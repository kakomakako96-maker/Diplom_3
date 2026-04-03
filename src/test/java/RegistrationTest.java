import io.qameta.allure.Step;
import org.example.Registration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    private String email;
    private String password;
    private String name;

    Registration user = new Registration();

    @BeforeEach
    void setUp() {
        name = String.valueOf(UUID.randomUUID());
        email = (UUID.randomUUID()) + "@mail.ru";
        password = "123456789";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        // переход на страницу тестового приложения
        driver.get("https://stellarburgers.education-services.ru/register");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @DisplayName("Регистрация пользователя")
    @Test
    void createUser() {

        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.getCurrentUrl().contains("/login"));

        assertTrue(driver.getCurrentUrl().contains("/login"),
                "Ожидался URL с /login, но получен: " + driver.getCurrentUrl());
    }

    @DisplayName("Ошибка при регистрации с паролем меньше 6 символов")
    @Test
    void createUserWithShortPassword(){

        password = "123";

        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();

        driver.findElement(user.getErrorMessage()).isDisplayed();

        assertTrue(driver.findElement(user.getErrorMessage()).isDisplayed(), "Ожидалось сообщение об ошибке для короткого пароля");
    }

    @Step("Ввод в поле имя")
    void setName(String name) {
        By inputXpath = user.getInputXpath();
        List<WebElement> allInputs = driver.findElements(inputXpath);
        allInputs.get(0).sendKeys(name);
    }

    @Step("Ввод в поле email")
    void setEmail(String email) {
        By inputXpath = user.getInputXpath();
        List<WebElement> allInputs = driver.findElements(inputXpath);
        allInputs.get(1).sendKeys(email);
    }

    @Step("Ввод в поле пароль")
    void setPassword(String password) {
        By passwordXpath = user.getPasswordXpath();
        driver.findElement(passwordXpath).sendKeys(password);
    }

    @Step("Клик на кнопку зарегистрироваться")
    void clickRegisterButton() {
        By buttonRegXpath = user.getButtonRegXpath();
        driver.findElement(buttonRegXpath).click();
    }
}
