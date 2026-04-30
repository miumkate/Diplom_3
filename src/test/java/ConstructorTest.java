import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;

    public void setup(){
        mainPage = new MainPage(driver);
        driver.get(mainPage.getUrlMainPage());
        driver.manage().window().maximize();
        mainPage.waitLoading();
    }

    @AfterEach
    public void cleanup(){
        driver.quit();
    }

    static Stream<Arguments> ingredientsList() {
        return Stream.of(
                Arguments.of("Булки", "chrome"),
                Arguments.of("Булки", "yandex"),
                Arguments.of("Соусы", "chrome"),
                Arguments.of("Соусы", "yandex"),
                Arguments.of("Начинки", "chrome"),
                Arguments.of("Начинки", "yandex")
        );
    }

    @ParameterizedTest
    @MethodSource("ingredientsList")
    @Description("Проверка, что работают переходы к разделам")
    public void ingredientsTest(String ingredientName, String browser){
        BrowserFactory getBrowser = new BrowserFactory();
        driver = getBrowser.getWebDriver(browser);

        setup();

        WebElement tabByName = mainPage.getTabByName(ingredientName);
        String beforeClickLocation;
        String afterClickLocation;

        beforeClickLocation = mainPage.getPositionScrolledMenu(tabByName.getText());

        // Если Tab некликабельный, то надо сделать кликабельным с проверками.
        if(!mainPage.checkClickable(tabByName)){
            // * передать TAB, чтобы сделать его доступным для клика
            mainPage.getTabClickable(tabByName);
        }

        clickCurrentTab(tabByName);
        afterClickLocation = mainPage.getPositionScrolledMenu(tabByName.getText());
        assertNotEquals(beforeClickLocation,afterClickLocation);
    }

    @Step
    @Description("Кликнуть на переданный в параметре таб")
    public void clickCurrentTab(WebElement element){
        mainPage.clickTab(element);
        // Проверить, что после клика таб стал некликабельный
        assertFalse(mainPage.checkClickable(element));
    }

}