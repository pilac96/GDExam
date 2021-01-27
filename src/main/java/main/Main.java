package main;

import handlers.UserInputHandler;
import models.Report;
import models.UserInput;

public class Main {
    public static void main(String[] args) {
        UserInputHandler inputHandler = new UserInputHandler();
        UserInput ui = inputHandler.tryGettingValidInput();

        Report report = new Report(ui);
        report.generate();
    }
}
