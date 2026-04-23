import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import pages.AccountProfilePage;
import pages.LoginPage;
import pages.MainPage;
import prepare.GeneratedData;

public class TransferToConstructorTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private AccountProfilePage accountProfilePage;
    private GeneratedData generatedData;

    @BeforeEach
    public void setup(){
        generatedData = new GeneratedData();
    }

    @AfterEach
    public void cleanup(){
        driver.quit();
    }

    @ParameterizedTest
    @CsvSource({
            "logo, chrome",
            "logo, yandex",
            "button, chrome",
            "button, yandex"
    })
    @Description("Переход из личного кабинета в конструктор по логотипу и по кнопке 'Конструктор'")
    public void transferFromAccountToConstructor(String portal, String browser){

        BrowserFactory getBrowser = new BrowserFactory();
        driver = getBrowser.getWebDriver(browser);

        loginPage = new LoginPage(driver);
        driver.get(loginPage.getUrlLoginPage());
        loginPage.completeLoginForm(generatedData.getExistingUser());

        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();

        accountProfilePage = new AccountProfilePage(driver);
        switch (portal){
            case "logo":
                accountProfilePage.clickConstructorLogo();
            case "button":
                accountProfilePage.clickConstructorButton();
        }

        mainPage = new MainPage(driver);
        mainPage.checkMainPage();
    }
}