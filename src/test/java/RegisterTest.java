import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.RegisterPage;
import prepare.GeneratedData;
import prepare.ModelUser;
import prepare.UserApi;

public class RegisterTest {

    private WebDriver driver;
    private RegisterPage registerPage;
    private GeneratedData generatedData;
    private ModelUser newUser;


    public void setup(){
        registerPage = new RegisterPage(driver);
        driver.get(registerPage.getUrlRegisterPage());
        registerPage.checkHeaderRegistrationPage();
        generatedData = new GeneratedData();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Успешно зарегистрировать нового пользователя")
    public void pageRegistrationTest(String browser){

        BrowserFactory getBrowser = new BrowserFactory();
        driver = getBrowser.getWebDriver(browser);
        setup();

        newUser = generatedData.getNewUser();

        registerPage.completeRegistrationForm(newUser);

        // проверить открытие страницы login
        LoginPage loginPage = new LoginPage(driver);
        driver.get(loginPage.getUrlLoginPage());
        loginPage.checkHeaderLoginPage();

        // удалить созданного пользователя
        UserApi userApi = new UserApi();
        userApi.deleteUserWithoutToken(newUser);
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Провермить ошибку при регистрации нового пользователя с неправильным паролем")
    public void pageRegistrationFailedTest(String browser){

        BrowserFactory getBrowser = new BrowserFactory();
        driver = getBrowser.getWebDriver(browser);
        setup();

        newUser = generatedData.getIncompleteUser();
        registerPage.completeRegistrationForm(newUser);
        registerPage.checkErrorMessage();
    }

    @AfterEach
    void exit(){
       driver.quit();
    }
}
