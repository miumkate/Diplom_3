package pages;
import prepare.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterPage extends BaseMethods {

    private WebDriver driver;
    private WebElement element;

    private final By inputField = By.xpath("//input[@class='text input__textfield text_type_main-default']");
    private final By inputPassword = By.xpath("//input[@class='text input__textfield text_type_main-default' and @type='password']");
    private final By errorMessage = By.xpath("//p[@class='input__error text_type_main-default']");
    private final By buttonRegistration =  By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Зарегистрироваться']");
    private final By headerRegister = By.tagName("h2");
    private final String pageHeader = "Регистрация";
    private final By loginButton = By.xpath("//a[@class='Auth_link__1fOlj' and text()='Войти'] ");


    public List<WebElement> getInputFields() {
        return driver.findElements(inputField);
    }

    public WebElement getInputNameField(){
        return getInputFields().get(0);
    }

    public WebElement getInputEmailField(){
        return getInputFields().get(1);
    }

    public By getErrorMessage() {
        return errorMessage;
    }

    public By getInputPassword() {
        return inputPassword;
    }

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickButtonRegistration(){
        driver.findElement(buttonRegistration).click();
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void checkHeaderRegistrationPage(){
        assertEquals(
                pageHeader,
                driver.findElement(headerRegister).getText(),
                String.format("Ожидается заголовок страницы '%s'", pageHeader)
                );
    }

    public void completeRegistrationForm(ModelUser user){
        getInputNameField().sendKeys(user.getName());
        getInputEmailField().sendKeys(user.getEmail());
        driver.findElement(inputPassword).sendKeys(user.getPassword());
        clickButtonRegistration();
    }

    public void checkErrorMessage(){
        element = driver.findElement(getErrorMessage());
        assertEquals(
                "Некорректный пароль",
                element.getText(),
                "Сообщения об ошибке не совпали.");
    }
}
