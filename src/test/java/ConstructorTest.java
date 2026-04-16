import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;

    public void setup(){
        mainPage = new MainPage(driver);
        driver.get(mainPage.getUrlMainPage());
        driver.manage().window().maximize();
    }

    @AfterEach
    public void cleanup(){
        driver.quit();
    }

    static Stream<Arguments> ingredientsList() {

        return Stream.of(
                Arguments.of(0, "Булки", "chrome"),
                Arguments.of(0, "Булки", "yandex"),
                Arguments.of(1, "Соусы", "chrome"),
                Arguments.of(1, "Соусы", "yandex"),
                Arguments.of(2, "Начинки", "chrome"),
                Arguments.of(2, "Начинки", "yandex")
        );
    }

    @ParameterizedTest
    @MethodSource("ingredientsList")
    @Description("Проверка, что работают переходы к разделам")
    public void ingredientsTest(int number, String ingredientName, String browser){

        BrowserFactory getBrowser = new BrowserFactory();
        driver = getBrowser.getWebDriver(browser);

        setup();

        WebElement element = mainPage.getIngredientElementsList().get(number);
        if (number!=0){
            element.click();
        }
        assertEquals(element.getText(), ingredientName,"В меню название ингредиента отличается от ожидаемого.");
    }
}