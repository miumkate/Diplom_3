import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import pages.AccountProfilePage;
import pages.*;
import prepare.*;

public class AccountProfileTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private AccountProfilePage accountProfilePage;
    private GeneratedData generatedData;

    public void setup(){
        generatedData = new GeneratedData();
        loginPage = new LoginPage(driver);
        driver.get(loginPage.getUrlLoginPage());
    }

    @AfterEach
    public void cleanup(){
       driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Проверка перехода по клику на «Личный кабинет»")
    public void accountProfileLoginTest(String browser) {
        BrowserFactory getBrowser = new BrowserFactory();
        driver = getBrowser.getWebDriver(browser);
        setup();

        loginPage.completeLoginForm(generatedData.getExistingUser());
        loginPage.clickAccountProfileButton(driver);

        accountProfilePage = new AccountProfilePage(driver);
        accountProfilePage.checkAccountProfileNamePage();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void accountProfileExitTest(String browser){
        BrowserFactory getBrowser = new BrowserFactory();
        driver = getBrowser.getWebDriver(browser);
        setup();

        loginPage.completeLoginForm(generatedData.getExistingUser());
        loginPage.clickAccountProfileButton(driver);

        accountProfilePage = new AccountProfilePage(driver);
        accountProfilePage.clickExitButton();

        loginPage = new LoginPage(driver);

        loginPage.checkHeaderLoginPage();
    }
}



