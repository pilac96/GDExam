package constants;

import java.time.format.DateTimeFormatter;

public class DateFormatConstants {
    public static final String VALID_DATE_FORMAT = "^\\d{1,2} [a-zA-Z]{3,9} \\d{4}, [a-zA-Z]{6,9}, ([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
    public static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMMM yyyy, EEEE, HH:mm");
    public static DateTimeFormatter DATE_FORMAT_2 = DateTimeFormatter.ofPattern("d MMMM yyyy - EEEE");
}
