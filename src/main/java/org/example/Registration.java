package org.example;

import org.openqa.selenium.By;

public class Registration {

    private final By inputXpath = By.xpath(".//input[@name = 'name']");
    private final By passwordXpath = By.xpath(".//input[@type = 'password']");
    private final By buttonRegXpath = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By errorMessage = By.xpath(".//p[text() = 'Некорректный пароль']");

    public Registration() {}

    public By getInputXpath() {
        return inputXpath;
    }

    public By getPasswordXpath() {
        return passwordXpath;
    }

    public By getButtonRegXpath() {
        return buttonRegXpath;
    }

    public By getErrorMessage() {
        return errorMessage;
    }
}

