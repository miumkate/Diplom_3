package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountProfilePage extends BaseMethods{

    private WebDriver driver;
    private final By profileDescription = By.xpath("//p[@class='Account_text__fZAIn text text_type_main-default']");
    private final String profileDescriptionText = "В этом разделе вы можете изменить свои персональные данные";
    private final By buttonExit = By.xpath("//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive' and @type='button']");
    private final By buttonList = By.xpath("//a[@class='AppHeader_header__link__3D_hX']");
    private final By logoConstructor = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");


    public AccountProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getInputFields() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonList));
        return driver.findElements(buttonList);
    }

    public WebElement getButtonConstructor(){
        return getInputFields().get(0);
    }

    public void checkAccountProfileNamePage(){
        //driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(profileDescription));
        String profileText = driver.findElement(profileDescription).getText();

        assertEquals(
                profileDescriptionText,
                profileText,
                "Не совпал тест Профиля."
        );
    }

    public void clickExitButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonExit));
        driver.findElement(buttonExit).click();
    }

    public void clickConstructorLogo(){
        getButtonConstructor().click();
    }

    public void clickConstructorButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(logoConstructor));
        driver.findElement(logoConstructor).click();
    }
}
