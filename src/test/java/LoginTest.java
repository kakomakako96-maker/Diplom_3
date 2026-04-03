import io.qameta.allure.Step;
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

public class LoginTest {

    private static WebDriver driver;
    private static String email;
    private static String password;
    private static String name;
    WebDriverWait wait;

    Login login = new Login();

    @BeforeEach
    void setUp() {
        name = String.valueOf(UUID.randomUUID());
        email = (UUID.randomUUID()) + "@mail.ru";
        password = "123456789";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        registration();
        driver.get("https://stellarburgers.education-services.ru");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @DisplayName("вход через кнопку \"Личный кабинет\"")
    @Test
    void loginPersonalAccount() {
        clickHrefPersonalAccountXpath();
        setEmail(email);
        setPassword(password);
        clickButtonLoginXpath();

        clickHrefPersonalAccountXpath();
        By profile = login.getProfile();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(profile));
        assertTrue(element.isDisplayed(),"Ошибка авторизации");
    }

    @DisplayName("вход через кнопку \"Войти в аккаунт\"на главной")
    @Test
    void loginButton() {
        clickHrefLoginButton();
        setEmail(email);
        setPassword(password);
        clickButtonLoginXpath();

        clickHrefPersonalAccountXpath();
        By profile = login.getProfile();
        WebElement profileVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(profile));
        assertTrue(profileVisible.isDisplayed(),"Ошибка авторизации");
    }

    @DisplayName("вход через кнопку в форме регистрации")
    @Test
    void loginButtonFormRegistrator() {
        clickButtonLoginFormRegistrator();
        clickHrefLoginXpath();
        setEmail(email);
        setPassword(password);
        clickButtonLoginXpath();

        clickHrefPersonalAccountXpath();
        By profile = login.getProfile();
        WebElement profileVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(profile));
        assertTrue(profileVisible.isDisplayed(),"Ошибка авторизации");
    }

    @DisplayName("вход через кнопку в форме восстановления пароля")
    @Test
    void loginForgotPassword() {
        clickHrefForgotPassword();
        clickHrefLoginXpath();
        setEmail(email);
        setPassword(password);
        clickButtonLoginXpath();

        clickHrefPersonalAccountXpath();
        By profile = login.getProfile();
        WebElement profileVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(profile));
        assertTrue(profileVisible.isDisplayed(),"Ошибка авторизации");
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

    @Step("Ввод email")
    void setEmail(String email) {
        By emailXpath = login.getEmailXpath();
        driver.findElement(emailXpath).sendKeys(email);
    }

    @Step("Ввод пароля")
    void setPassword(String password) {
        By passwordXpath = login.getPasswordXpath();
        driver.findElement(passwordXpath).sendKeys(password);
    }

    @Step("Нажатие на кнопку \"Войти\" на странице авторизации")
    void clickButtonLoginXpath(){
        By buttonLoginXpath = login.getButtonLoginXpath();
        driver.findElement(buttonLoginXpath).click();
    }

    @Step("Нажатие на \"Личный кабинет\" на главной странице")
    void clickHrefPersonalAccountXpath() {
        By hrefPersonalAccountXpath = login.getHrefPersonalAccountXpath();
        driver.findElement(hrefPersonalAccountXpath).click();
    }

    @Step("Нажатие на \"Войти в аккаунт\" на главной странице")
    void clickHrefLoginButton() {
        By loginButton = login.getButtonAccountLogin();
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на \"Зарегистрироваться\"")
    void clickButtonLoginFormRegistrator(){
        driver.get("https://stellarburgers.education-services.ru/login");
        By hrefRegisterXpath = login.getHrefRegisterXpath();
        driver.findElement(hrefRegisterXpath).click();
    }

    @Step("Нажатие на \"Войти\" в форме регистрации")
     void clickHrefLoginXpath(){
        By hrefLoginXpath = login.getHrefLoginXpath();
        driver.findElement(hrefLoginXpath).click();
    }

    @Step("Нажатие на \"Восстановления пароля\" на странице авторизации")
    void clickHrefForgotPassword(){
        driver.get("https://stellarburgers.education-services.ru/login");
        By xpathForgotPassword = login.getXpathForgotPassword();
        driver.findElement(xpathForgotPassword).click();
    }

}