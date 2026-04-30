package pages;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import prepare.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends BaseMethods{

    private WebDriver driver;
    private final By headerLogin = By.xpath("//h2[text()='Вход']");
    private final String pageHeader = "Вход";
    private final By fieldLogin = By.xpath("//input[@class='text input__textfield text_type_main-default']");
    private final By fieldPassword = By.xpath("//input[@class='text input__textfield text_type_main-default' and @type='password']");
    private final By buttonLogin = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' " +
            "and text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonLogin(){
        driver.findElement(buttonLogin).click();
    }

    public void checkHeaderLoginPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(headerLogin));

        assertEquals(
                pageHeader,
                driver.findElement(headerLogin).getText(),
                String.format("Ожидается заголовок страницы '%s'", pageHeader)
        );
    }

    public void completeLoginForm(ModelUser user){
        driver.manage().window().maximize();
        driver.findElement(fieldLogin).sendKeys(user.getEmail());
        driver.findElement(fieldPassword).sendKeys(user.getPassword());
        clickButtonLogin();
    }
}
