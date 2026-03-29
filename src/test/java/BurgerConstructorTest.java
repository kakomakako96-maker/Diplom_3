import io.qameta.allure.Step;
import org.example.BurgerConstructor;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BurgerConstructorTest {

    private WebDriver driver;
    BurgerConstructor constructor = new BurgerConstructor();
    WebDriverWait wait;

    @BeforeEach
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.education-services.ru");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @DisplayName("Проверка перехода в раздел \"Начинки\"")
    @Test
    void spanTopping(){
        clickToppingSpan();
        By topping = constructor.getToppingSearch();
        WebElement toppingVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(topping));
        assertTrue(toppingVisible.isDisplayed(),"Начинки не найдены");
    }

    @DisplayName("Проверка перехода в раздел \"Соусы\"")
    @Test
    void spanSouse(){
        clickSouseSpan();
        By souse = constructor.getSouseSearch();
        WebElement souseVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(souse));
        assertTrue(souseVisible.isDisplayed(),"Соусы не найдены");
    }

    @DisplayName("Проверка перехода в раздел \"Булки\"")
    @Test
    void spanBun(){
        clickToppingSpan();
        clickBunSpan();
        By bun = constructor.getBunSearch();
        WebElement bunVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(bun));
        assertTrue(bunVisible.isDisplayed(),"Булки не найдены");
    }

    @Step("Нажатие на колонку \"Булки\"")
    void clickBunSpan(){
        driver.findElement(constructor.getBunXpath()).click();
    }

    @Step("Нажатие на колонку \"Соусы\"")
    void clickSouseSpan(){
        driver.findElement(constructor.getSouseXpath()).click();
    }

    @Step("Нажатие на колонку \"Начинки\"")
    void clickToppingSpan(){
        driver.findElement(constructor.getToppingsXpath()).click();
    }
}
