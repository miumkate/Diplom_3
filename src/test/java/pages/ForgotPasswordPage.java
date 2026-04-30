package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;
    private final By loginButton = By.xpath("//a[@class='Auth_link__1fOlj' and text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
}
