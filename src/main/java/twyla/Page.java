package twyla;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class Page {

    public static final String BASE_URL = "http://localhost:4200";

    public static WebDriver driver;
    static String directoryPath = System.getProperty("user.dir");

    public Page() {

    }

    public static void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", directoryPath
            + "\\lib\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);

    }

    public void openPage(String url) {
        driver.navigate().to(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public static void refresh() {
        driver.navigate().refresh();

    }

    public static void quitDriver() {
        driver.quit();
    }

    public static void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
