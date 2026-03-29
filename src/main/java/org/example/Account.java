package org.example;

import org.openqa.selenium.By;

public class Account {

    private final By profile = By.xpath(".//p[contains(., 'персональные данные')]");
    private final By buttonQuit = By.xpath(".//button[text() = 'Выход']");
    private final By hrefPersonalAccountXpath = By.xpath(".//a[@href = '/account']");
    private final By aXpathAll = By.xpath(".//a[@href = '/']");
    private final By mainXpath = By.xpath(".//h1[text() = 'Соберите бургер']");

    public Account() {
    }

    public By getButtonQuit() {
        return buttonQuit;
    }

    public By getProfile() {
        return profile;
    }

    public By getHrefPersonalAccountXpath() {
        return hrefPersonalAccountXpath;
    }

    public By getaXpathAll() {
        return aXpathAll;
    }

    public By getMainXpath() {
        return mainXpath;
    }
}