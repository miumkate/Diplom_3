import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;
    private List<String> resultActual;
    private List<String> resultExpected;

    public void setup(){
        mainPage = new MainPage(driver);
        driver.get(mainPage.getUrlMainPage());
        driver.manage().window().maximize();
        mainPage.waitLoading();
        resultActual = new ArrayList<>();
        resultExpected = new ArrayList<>();
    }

    @AfterEach
    public void cleanup(){
        driver.quit();
    }

    static Stream<Arguments> browserList() {

        return Stream.of(
                Arguments.of("chrome"),
                Arguments.of("yandex")
        );
    }

    @ParameterizedTest
    @MethodSource("browserList")
    @Description("Проверка, что работают переходы к разделам")
    public void ingredientsTest(String browser){
        WebElement tabNotClickable = null;
        String parentAttribute;
        String tabName;


        BrowserFactory getBrowser = new BrowserFactory();
        driver = getBrowser.getWebDriver(browser);

        setup();

        List<WebElement> elementsList = mainPage.getIngredientElementsList();
        int countTabs = elementsList.size();

        //Перебрать все табы
        for (WebElement tab : elementsList) {
            resultExpected.add(tab.getText());
            parentAttribute = mainPage.getParentAttributeClass(tab);
            assertNotNull(parentAttribute);

            // Проверить таб на кликабельность
            if(parentAttribute.contains("current")){ // Если не кликабельный - сохранить
                tabNotClickable = tab;
            }
            else { // Если кликабельный - кликнуть и получить название таба
                tabName= mainPage.clickTab(tab);
                setResultActual(tabName);

            }
        }

        assertNotNull(tabNotClickable);
        tabName = mainPage.clickTab(tabNotClickable);
        setResultActual(tabName);

        checkResults(countTabs,resultActual, resultExpected);
    }

    @Step
    @Description("Проверка результата перехода по табам")
    public void checkResults(int countTabs, List<String> resultActual, List<String> resultExpected){

        assertEquals(countTabs, getResultActual().size(), "Не все элементы проверены");

        for (String element : resultActual) {
            assertTrue(resultExpected.contains(element));
        }
    }

    @Step
    @Description("Добавление в список проверенного таба")
    public void setResultActual(String tabName) {
        resultActual.add(tabName);
    }

    @Step
    @Description("Получить список проверенных табов")
    public List<String> getResultActual(){
        return resultActual;
    }

}