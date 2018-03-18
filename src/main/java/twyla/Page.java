package twyla;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Page {

   public static WebDriver driver;
    static String directoryPath = System.getProperty("user.dir");

    public Page() {

    }

    public String getBaseUrl() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(getFileAsString());
            JSONObject jsonObject = (JSONObject) obj;
            return  jsonObject.get("url").toString();
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private String getFileAsString() {
        try {
            return new String(Files.readAllBytes(Paths.get("src", "main", "resources", "configuration.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
