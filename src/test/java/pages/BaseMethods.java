package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseMethods {

    private final String baseUrl = "https://stellarburgers.education-services.ru/";
    private final String urlForgotPasswordPage = baseUrl + "forgot-password";
    private final String urlLoginPage = baseUrl + "login";
    private final String urlMainPage = baseUrl;
    private final String urlRegisterPage = baseUrl + "register";
    private final By accountProfileButton = By.xpath("//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");
    private WebElement element;

    public void clickAccountProfileButton(WebDriver driver){
        driver.manage().window().maximize();
        element = driver.findElement(accountProfileButton);
        element.click();
    }

    public String getUrlLoginPage() {
        return urlLoginPage;
    }

    public String getUrlMainPage() {
        return urlMainPage;
    }

    public String getUrlForgotPasswordPage() {
        return urlForgotPasswordPage;
    }

    public String getUrlRegisterPage() {
        return urlRegisterPage;
    }
}
