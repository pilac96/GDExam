package models;

import constants.Constants;
import constants.DateFormatConstants;
import handlers.DataLoader;
import handlers.DateHandler;

import java.time.*;
import java.util.List;

public class Report {
    List<Student> students = DataLoader.loadStudents("src/main/resources/data/students.json");

    private String date;
    private ReportType reportType;

    public Report(String date, ReportType reportType) {
        this.date = date;
        this.reportType = reportType;
    }

    public Report() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public void generate() {
        switch(getReportType()){
            case SHORT:
                generateShortReport();
                break;
            case FULL:
                generateFullReport();
                break;
        }
    }

    private void generateFullReport() {
        for(Student s: students){
            System.out.println(String.format(Constants.FULL_REPORT_MESSAGE, s.getName(), s.getCurriculum(), totalHoursOfTraining(s), s.getStartDate(), calculateEndDate(s),trainingStatus(s)));
        }
    }

    private String calculateEndDate(Student s) {
        LocalDateTime startDate = DateHandler.parseStartedDate(s.getStartDate());
        return startDate.plusDays(calculateWorkingDays(startDate, DateHandler.parseEnteredDate(date))).format(DateFormatConstants.DATE_FORMAT_2);
    }

    private void generateShortReport() {
        for(Student s: students){
            System.out.println(String.format(Constants.SHORT_REPORT_MESSAGE, s.getName(), s.getCurriculum(), trainingStatus(s)));
        }
    }

    private String trainingStatus(Student s){
        int leftHoursToFinishTraining = passedHoursFromStart(s) - totalHoursOfTraining(s);
        int days = Math.abs(leftHoursToFinishTraining / 8);
        int hours = Math.abs(leftHoursToFinishTraining % 8);

        if(leftHoursToFinishTraining < 0)
            return String.format(Constants.TRAINING_NOT_FINISHED, returnDaysHoursMessage(days, hours));
        else
            return String.format(Constants.TRAINING_FINISHED, returnDaysHoursMessage(days, hours));

    }

    private String returnDaysHoursMessage(int days, int hours){
        String message = "";
        if(days > 0)
            message += String.format(" %d days", days);
        if(hours > 0)
            message += String.format(" %d hours", hours);
        return message;
    }

    private int totalHoursOfTraining(Student s){
        int total = 0;
        for(Course c: s.getCourses())
            total += Integer.parseInt(c.getDuration());
        return total;
    }

    private int passedHoursFromStart(Student s){
        LocalDateTime startDate = DateHandler.parseStartedDate(s.getStartDate());
        LocalDateTime enteredDate = DateHandler.parseEnteredDate(date);

        int noOfWorkingDays = calculateWorkingDays(startDate, enteredDate);
        int noOfWorkingHours = noOfWorkingDays * 8;

        int todayWorkHours = enteredDate.getHour() - 10;
        return noOfWorkingHours + todayWorkHours;
    }

    private int calculateWorkingDays(LocalDateTime startDate, LocalDateTime enteredDate) {
        int workDays = 0;
        do {
            startDate = startDate.plusDays(1);
            if(!(startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) && !(startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
                workDays += 1;
            }
        } while(startDate.isBefore(enteredDate));
        return (workDays-1);
    }

}
