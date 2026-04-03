import io.qameta.allure.Step;
import org.example.Account;
import org.example.Login;
import org.example.Registration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {

    private static WebDriver driver;
    private static String email;
    private static String password;
    private static String name;
    WebDriverWait wait;

    Account login = new Account();

    @BeforeEach
    void setUp() {
        name = String.valueOf(UUID.randomUUID());
        email = (UUID.randomUUID()) + "@mail.ru";
        password = String.valueOf(UUID.randomUUID());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        registration();
        login();
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @DisplayName("Проверь переход по клику на «Личный кабинет».")
    @Test
    void getLoginPersonal() {
        clickHrefPersonalAccountXpath();
        By profile = login.getProfile();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(profile));
        assertTrue(element.isDisplayed(), "Ошибка перехода в личный кабинет");
    }

    @DisplayName("Переход из личного кабинета в конструктор")
    @Test
    void getBurgerConstructor() {
        clickHrefPersonalAccountXpath();
        clickConstructorBurger();
        By mainXpath = login.getMainXpath();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(mainXpath));
        assertTrue(element.isDisplayed(), "Ошибка перехода в конструктор");
    }

    @DisplayName("Проверь переход по клику на логотип Stellar Burgers.")
    @Test
    void getStellarBurgers() {
        clickHrefPersonalAccountXpath();
        clickLogoStellarBurger();
        By mainXpath = login.getMainXpath();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(mainXpath));
        assertTrue(element.isDisplayed(), "Ошибка перехода в конструктор через логотип");
    }

    @DisplayName("Выход из аккаунта")
    @Test
    void quitAccount() {
        clickHrefPersonalAccountXpath();
        clickButtonQuit();
        By profile = login.getProfile();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(profile));
        assertTrue(element.isDisplayed(), "Ошибка выхода");

    }

    @Step("Нажатие на \"Личный кабинет\" на главной странице")
    void clickHrefPersonalAccountXpath() {
        By hrefPersonalAccountXpath = login.getHrefPersonalAccountXpath();
        driver.findElement(hrefPersonalAccountXpath).click();
    }

    @Step("Регистрация пользователя")
    void registration() {
        driver.get("https://stellarburgers.education-services.ru/register");
        Registration user = new Registration();
        By inputXpathName = user.getInputXpath();
        List<WebElement> inputsAll = driver.findElements(inputXpathName);
        inputsAll.get(0).sendKeys(name);
        inputsAll.get(1).sendKeys(email);
        By passwordXpath = user.getPasswordXpath();
        driver.findElement(passwordXpath).sendKeys(password);
        By buttonRegXpath = user.getButtonRegXpath();
        driver.findElement(buttonRegXpath).click();
    }

    @Step("Авторизация пользователя")
    void login() {
        driver.get("https://stellarburgers.education-services.ru/login");
        Login user = new Login();
        By emailXpath = user.getEmailXpath();
        driver.findElement(emailXpath).sendKeys(email);
        By passwordXpath = user.getPasswordXpath();
        driver.findElement(passwordXpath).sendKeys(password);
        By buttonLoginXpath = user.getButtonLoginXpath();
        driver.findElement(buttonLoginXpath).click();
    }

    @Step("Нажатие на кнопку выхода в личном кабинете")
    void clickButtonQuit() {
        By buttonQuit = login.getButtonQuit();
        driver.findElement(buttonQuit).click();
    }

    @Step("Нажатие на кнопку Конструктор")
    void clickConstructorBurger() {
        By aXpathAll = login.getaXpathAll();
        List<WebElement> inputsAll = driver.findElements(aXpathAll);
        inputsAll.get(0).click();
    }

    @Step("Нажатие на логотип")
    void clickLogoStellarBurger() {
        By aXpathAll = login.getaXpathAll();
        List<WebElement> inputsAll = driver.findElements(aXpathAll);
        inputsAll.get(1).click();
    }

}
