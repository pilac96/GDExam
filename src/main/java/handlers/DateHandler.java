package handlers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHandler {
    public static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMMM yyyy, EEEE, HH:mm");
    public static DateTimeFormatter DATE_FORMAT_2 = DateTimeFormatter.ofPattern("d MMMM yyyy - EEEE");

    public static LocalDateTime parseEnteredDate(String stringDate) {
        return LocalDateTime.parse(stringDate.trim(), DATE_FORMAT);
    }

    public static LocalDateTime parseStartedDate(String stringDate) {
        return LocalDate.parse(stringDate.trim(), DATE_FORMAT_2).atTime(10, 00);
    }

}
