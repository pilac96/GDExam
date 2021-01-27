package handlers;

import models.ReportType;
import models.UserInput;

import java.util.Scanner;

public class UserInputHandler {
    private static final String USER_INPUT_DATE_MESSAGE = "Enter the date for which you want to check the report\nDate format has to be: dd MMMM yyyy, WEEK_DAY, HH:MM\neg. 27 January 2021, Wednesday, 15:00 >>>";
    private static final String INVALID_INPUT_ERROR_MESSAGE = "Entered date has invalid format!";
    private static final String USER_INPUT_TYPE_REPORT_FORMAT_MESSAGE = "Enter the type of format of report - 0 for short, everything else for full report >>>";
    private static final String VALID_DATE_FORMAT = "^\\d{1,2} [a-zA-Z]{3,9} \\d{4}, [a-zA-Z]{6,9}, ([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";

    private static Scanner input = new Scanner(System.in);

    private static String getUserInput(String message) {
        System.out.println(message);
        return input.nextLine().trim();
    }

    public UserInput tryGettingValidInput() {
        UserInput ui = new UserInput();
        while (true) {
            String answer1 = getUserInput(USER_INPUT_DATE_MESSAGE);
            if (!answer1.matches(VALID_DATE_FORMAT)) {
                System.out.println(INVALID_INPUT_ERROR_MESSAGE);
                continue;
            }

            ui.userInputs.put("date", answer1);

            String answer2 = getUserInput(USER_INPUT_TYPE_REPORT_FORMAT_MESSAGE);

            if (answer2.isEmpty() || answer2.equals("0")) {
                ui.userInputs.put("reportType", ReportType.SHORT);
            } else {
                ui.userInputs.put("reportType", ReportType.FULL);
            }
            break;
        }
        return ui;
    }
}
