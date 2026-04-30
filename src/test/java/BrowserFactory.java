import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {

    private WebDriver getYandexDriver(){
        System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\yandexdriver.exe");
        return new ChromeDriver();
    }

    private WebDriver getChromeDriver(){
        System.clearProperty("webdriver.chrome.driver");
        return new ChromeDriver();
    }

    public WebDriver getWebDriver(String browserName){
        switch (browserName){
            case "yandex":
                return getYandexDriver();
            case "chrome":
            default:
                return getChromeDriver();
        }
    }
}
