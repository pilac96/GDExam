package models;

import handlers.DataLoader;
import handlers.DateHandler;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class Report {
    private static final String TRAINING_NOT_FINISHED = "Training is not finished. %s are left until the end";
    private static final String TRAINING_FINISHED = "Training completed. %s have passed since the end";
    private static final String SHORT_REPORT_MESSAGE = "%s (%s) - %s";
    private static final String FULL_REPORT_MESSAGE = "%s (%s) - duration: %s hrs, started: %s, end date: %s, status: %s";
    private List<Student> students;

    private UserInput ui;

    public Report(UserInput ui) {
        this();
        this.ui = ui;
    }

    public Report() {
        try {
            students = DataLoader.loadStudents("src/main/resources/data/students.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ReportType getReportType() {
        return (ReportType) ui.userInputs.get("reportType");
    }

    private String getDate() {
        return (String) ui.userInputs.get("date");
    }

    public void generate() {
        switch (getReportType()) {
            case SHORT:
                generateShortReport();
                break;
            case FULL:
                generateFullReport();
                break;
            default:
                throw new InvalidParameterException("Invalid report type!");
        }
    }

    private void generateFullReport() {
        for (Student s : students) {
            System.out.println(String.format(FULL_REPORT_MESSAGE, s.getName(), s.getCurriculum(), totalHoursOfTraining(s), s.getStartDate(), calculateEndDate(s), trainingStatus(s)));
        }
    }

    public String calculateEndDate(Student s) {
        LocalDateTime startDate = DateHandler.parseStartedDate(s.getStartDate());
        return startDate.plusDays(calculateWorkingDays(startDate, DateHandler.parseEnteredDate(getDate()))).format(DateHandler.DATE_FORMAT_2);
    }

    private void generateShortReport() {
        for (Student s : students) {
            System.out.println(String.format(SHORT_REPORT_MESSAGE, s.getName(), s.getCurriculum(), trainingStatus(s)));
        }
    }

    private String trainingStatus(Student s) {
        int leftHoursToFinishTraining = passedHoursFromStart(s) - totalHoursOfTraining(s);
        int days = Math.abs(leftHoursToFinishTraining / 8);
        int hours = Math.abs(leftHoursToFinishTraining % 8);

        return String.format((leftHoursToFinishTraining < 0) ? TRAINING_NOT_FINISHED : TRAINING_FINISHED, returnDaysHoursMessage(days, hours));
    }

    private String returnDaysHoursMessage(int days, int hours) {
        String message = "";
        if (days > 0)
            message += String.format(" %d days", days);
        if (hours > 0)
            message += String.format(" %d hours", hours);
        return message;
    }

    public int totalHoursOfTraining(Student s) {
        int total = 0;
        for (Course c : s.getCourses())
            total += Integer.parseInt(c.getDuration());
        return total;
    }

    private int passedHoursFromStart(Student s) {
        LocalDateTime startDate = DateHandler.parseStartedDate(s.getStartDate());
        LocalDateTime enteredDate = DateHandler.parseEnteredDate(getDate());

        int noOfWorkingDays = calculateWorkingDays(startDate, enteredDate);
        int noOfWorkingHours = noOfWorkingDays * 8;

        int todayWorkHours = enteredDate.getHour() - 10;
        return noOfWorkingHours + todayWorkHours;
    }

    public int calculateWorkingDays(LocalDateTime startDate, LocalDateTime enteredDate) {
        int workDays = 0;
        do {
            startDate = startDate.plusDays(1);
            if (!(startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) && !(startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
                workDays += 1;
            }
        } while (startDate.isBefore(enteredDate));
        return (workDays - 1);
    }

}