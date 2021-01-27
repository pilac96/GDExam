package main;

import handlers.DataLoader;
import handlers.UserInputHandler;
import models.Report;
import models.Student;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = DataLoader.loadStudents("src/main/resources/data/students.json");

        Report report = UserInputHandler.tryGettingValidInputAndReturnReport();
        report.generate();
    }
}
