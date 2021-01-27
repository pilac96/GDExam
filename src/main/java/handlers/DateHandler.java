package handlers;

import constants.DateFormatConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateHandler {

    public static LocalDateTime parseEnteredDate(String stringDate){
        return LocalDateTime.parse(stringDate.trim(), DateFormatConstants.DATE_FORMAT);
    }

    public static LocalDateTime parseStartedDate(String stringDate){
        return LocalDate.parse(stringDate.trim(), DateFormatConstants.DATE_FORMAT_2).atTime(10, 00);
    }

}
