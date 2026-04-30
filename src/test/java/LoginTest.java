
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import pages.*;
import prepare.GeneratedData;
import prepare.ModelUser;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private GeneratedData generatedData;
    private ModelUser newUser;
    private BaseMethods baseMethods;


    @Step
    @Description("Подготовка драйвера")
    public void prepareDriver(String browser){
        baseMethods = new BaseMethods();
        BrowserFactory getBrowser = new BrowserFactory();
        driver = getBrowser.getWebDriver(browser);
        driver.manage().window().maximize();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("вход через кнопку «Личный кабинет»,")
    public void loginFromMainPageTest(String browser){

        prepareDriver(browser);

        driver.get(baseMethods.getUrlMainPage());
        MainPage mainPage = new MainPage(driver);
        mainPage.checkMainPage();
        mainPage.clickAccountButton();

        loginToAccount();

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("вход по кнопке «Войти в аккаунт» на главной")
    public void loginEnterButtonFromMainPageTest(String browser){

        prepareDriver(browser);

        driver.get(baseMethods.getUrlMainPage());
        MainPage mainPage = new MainPage(driver);
        mainPage.checkMainPage();
        mainPage.clickEnterToAccountButton();

        loginToAccount();

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("вход через кнопку в форме регистрации")
    public void loginFromRegisterPageTest(String browser){

        prepareDriver(browser);

        driver.get(baseMethods.getUrlRegisterPage());
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginButton();

        loginToAccount();

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("вход через кнопку в форме восстановления пароля")
    public void loginFromForgotPasswordPageTest(String browser){

        prepareDriver(browser);

        driver.get(baseMethods.getUrlForgotPasswordPage());
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginButton();

        loginToAccount();
    }

    public void loginToAccount(){
        loginFormToAccount();
        transferToAccountAfterLogin();
        checkAccountProfile();
    }

    @Step("Логинация на форме входа")
    public void loginFormToAccount() {
        generatedData = new GeneratedData();
        loginPage = new LoginPage(driver);
        newUser = generatedData.getExistingUser();
        loginPage.completeLoginForm(newUser);
    }
    @Step("Переход в личный кабинет залогиненным пользователем")
    public void transferToAccountAfterLogin() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();
    }
    @Step("Проверка, что открыт личный кабинет")
    public void checkAccountProfile() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        accountProfilePage.checkAccountProfileNamePage();
    }

    @AfterEach
    public void cleanup(){
        driver.quit();
    }

}
