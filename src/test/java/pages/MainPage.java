package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage extends BaseMethods{

    private WebDriver driver;
    private final By ingredientElement = By.xpath("//span[@class='text text_type_main-default']");
    private final By enterToAccountButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg'" +
            " and text()='Войти в аккаунт']");
    private final By accountButton = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getIngredientElementsList() {
        return driver.findElements(ingredientElement);
    }

    public void clickEnterToAccountButton(){
        driver.findElement(enterToAccountButton).click();
    }

    public void clickAccountButton(){
        driver.findElement(accountButton).click();
    }

    public void checkMainPage(){
        assertEquals(
                getUrlMainPage(),
                driver.getCurrentUrl(),
                "Ссылка на страницу отличается от ожидаемой. " + driver.getCurrentUrl());
    }
}
