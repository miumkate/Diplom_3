package pages;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainPage extends BaseMethods{

    private WebDriver driver;
    private final By ingredientElement = By.xpath("//span[@class='text text_type_main-default']");
    private final By enterToAccountButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg'" +
            " and text()='Войти в аккаунт']");
    private final By accountButton = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    private final By burgerIngredient = By.xpath("//h2[@class='text text_type_main-medium mb-6 mt-10']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getTabClickable(WebElement element){
        List<WebElement> elementList = getIngredientElementsList();
        WebElement otherTab = null;

        for (WebElement tab : elementList){
            if (!tab.getText().equals(element.getText())){
                otherTab = tab;
                break;
            }
        }

        otherTab.click();
    }

    @Step
    @Description("Получить координаты элемента меню, который скроллится при нажатии на таб")
    public String getPositionScrolledMenu(String ingredientName){
        List<WebElement> elementsList =  driver.findElements(burgerIngredient);
        String location = "";
        for (WebElement tab : elementsList) {
            if(tab.getText().equals(ingredientName)){
                location = tab.getLocation().toString();
            }
        }
        return location;
    }

    public List<WebElement> getIngredientElementsList() {
        return driver.findElements(ingredientElement);
    }

    public WebElement getTabByName(String ingredientName){
        List<WebElement> elementList = getIngredientElementsList();
        WebElement result = null;

        for (WebElement tab : elementList){
            if (tab.getText().equals(ingredientName)){
                result = tab;
            }
        }
        return result;
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

    public void waitLoading(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(ingredientElement));
        } catch (TimeoutException e) {
            System.out.println("Элементы ингредиентов не найдены на странице");
        }
    }
    public String getParentAttributeClass(WebElement tab){
        WebElement parentElement = tab.findElement(By.xpath("./.."));
        return parentElement.getAttribute("class");
    }

    @Step
    @Description("Проверить кликабельность таба")
    public boolean checkClickable(WebElement tab){
        boolean result = false;
        String parentAttribute = getParentAttributeClass(tab);
        assertNotNull(parentAttribute);

        // Проверить таб на кликабельность по родительскому классу
        if(!parentAttribute.contains("current")){
            result = true;
        }
        return result;
    }

    @Step
    @Description("Кликнуть по табу")
    public void clickTab(WebElement tab){
            tab.click();
    }

}
