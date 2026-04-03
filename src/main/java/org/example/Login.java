package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login {

    private final By emailXpath = By.xpath(".//input[@name = 'name']");
    private final By passwordXpath = By.xpath(".//input[@type = 'password']");
    private final By buttonLoginXpath = By.xpath(".//button[text() = 'Войти']");
    private final By hrefPersonalAccountXpath = By.xpath(".//a[@href = '/account']");
    private final By hrefRegisterXpath = By.xpath(".//a[contains(text(), 'Зарегистрироваться')]");
    private final By hrefLoginXpath = By.xpath(".//a[@href = '/login']");
    private final By buttonAccountLogin = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By buttonQuit = By.xpath(".//button[text() = 'Выход']");
    private final By profile = By.xpath(".//p[contains(., 'персональные данные')]");
    private final By xpathForgotPassword = By.xpath(".//a[@href = '/forgot-password']");

    public Login() {}

    public By getEmailXpath() {
        return emailXpath;
    }

    public By getPasswordXpath() {
        return passwordXpath;
    }

    public By getButtonLoginXpath() {
        return buttonLoginXpath;
    }

    public By getHrefPersonalAccountXpath() {
        return hrefPersonalAccountXpath;
    }

    public By getHrefLoginXpath() {
        return hrefLoginXpath;
    }

    public By getProfile() {
        return profile;
    }

    public By getButtonAccountLogin() {
        return buttonAccountLogin;
    }

    public By getButtonQuit() {
        return buttonQuit;
    }

    public By getHrefRegisterXpath() {
        return hrefRegisterXpath;
    }

    public By getXpathForgotPassword() {
        return xpathForgotPassword;
    }
}