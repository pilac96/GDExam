import models.Course;
import models.Report;
import models.Student;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportTest {
    private List<Course> courses = new ArrayList<Course>(Arrays.asList(
            new Course("Java", "16"),
            new Course("JDBC", "12"),
            new Course("Hibernate", "14")
    ));
    private Student student = new Student("John Snow", "Java Developer", "2 June 2020 - Tuesday", courses);

    @Test
    public void verifyCalculationWorkingDays() {
        Report report = new Report();
        int expectedResult = 12;

        int actualResult = report.calculateWorkingDays(
                LocalDateTime.of(2021, 1, 24, 14, 30),
                LocalDateTime.of(2021, 2, 10, 14, 30));

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void validateTotalHoursOfTrainingCalculation() {
        Report report = new Report();
        int expectedResult = 42;
        int actualResult = report.totalHoursOfTraining(student);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void negativeTotalHoursOfTrainingCalculation() {
        Report report = new Report();
        int expectedResult = 40;
        int actualResult = report.totalHoursOfTraining(student);
        Assert.assertFalse(actualResult == expectedResult);
    }

}
