package org.example;


import org.openqa.selenium.By;


public class BurgerConstructor {

    private final By bunXpath = By.xpath(".//span[text() = 'Булки']");
    private final By souseXpath = By.xpath(".//span[text() = 'Соусы']");
    private final By toppingsXpath = By.xpath(".//span[text() = 'Начинки']");
    private final By toppingSearch = By.xpath(".//h2[text()='Начинки']");
    private final By souseSearch = By.xpath(".//h2[text()='Соусы']");
    private final By bunSearch = By.xpath(".//h2[text()='Булки']");

    public BurgerConstructor() {
    }

    public By getBunXpath() {
        return bunXpath;
    }

    public By getSouseXpath() {
        return souseXpath;
    }

    public By getToppingsXpath() {
        return toppingsXpath;
    }

    public By getToppingSearch() {
        return toppingSearch;
    }

    public By getSouseSearch() {
        return souseSearch;
    }

    public By getBunSearch() {
        return bunSearch;
    }
}
