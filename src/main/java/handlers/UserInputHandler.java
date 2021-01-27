package handlers;

import constants.Constants;
import constants.DateFormatConstants;
import models.Report;
import models.ReportType;

import java.util.Scanner;

public class UserInputHandler {

    private static Scanner input = new Scanner(System.in);

    public static Report tryGettingValidInputAndReturnReport(){
        Report report = new Report();
        while(true) {
            String answer1 = getUserInput(Constants.USER_INPUT_DATE_MESSAGE);
            if(!answer1.matches(DateFormatConstants.VALID_DATE_FORMAT)) {
                System.out.println(Constants.INVALID_INPUT_ERROR_MESSAGE);
                continue;
            }
            report.setDate(answer1);

            String answer2 = getUserInput(Constants.USER_INPUT_TYPE_REPORT_FORMAT_MESSAGE);
            if(answer2.isEmpty() || answer2.equals("0")) {
                report.setReportType(ReportType.SHORT);
            } else {
                report.setReportType(ReportType.FULL);
            }
            break;
        }
        return report;
    }

    private static String getUserInput(String message) {
        System.out.println(message);
        return input.nextLine().trim();
    }
}
