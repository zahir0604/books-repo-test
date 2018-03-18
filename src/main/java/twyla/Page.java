package twyla;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

/**
 * The class contains the common functions
 *
 * @author customer.admin
 */
public abstract class Page {

    public static final String BASE_URL = "http://localhost:4200";

    public static WebDriver driver;
    static String directoryPath = System.getProperty("user.dir");
    public static Properties myProperties;

    public Page() {
        System.setProperty("webdriver.chrome.driver", directoryPath
            + "\\lib\\chromedriver.exe");

    }

    public void initializeDriver() {
        if (driver == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            driver = new ChromeDriver(chromeOptions);
        }
    }

    public void openPage(String url) {

        initializeDriver();
        driver.navigate().to(url);
    }

    /**
     * To get the Title of the page
     *
     * @return - It return the title of the page
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * To refresh the page
     */
    public static void refresh() {
        driver.navigate().refresh();

    }

    /**
     * To Close the driver currently working on
     */
    public static void quitDriver() {
        driver.quit();
    }

    /**
     * To make the driver wait for a specified time
     *
     * @param time amount of time for which the driver has to wait
     */
    public static void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Config of Json file
     */
    public static void readConfiguration() {
        String jsonFilePath = directoryPath
            + "/src/java/resources/configuration.json";

        File configuration = new File(jsonFilePath);
        myProperties = new Properties();

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(configuration));
            JSONObject configObj = (JSONObject) obj;
            for (Iterator<String> i = configObj.keySet().iterator(); i
                .hasNext(); ) {
                String key = i.next();
                myProperties.setProperty(key, (String) configObj.get(key));
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ParseException e1) {
            System.err.println(e1.getMessage());
        }

    }

}
