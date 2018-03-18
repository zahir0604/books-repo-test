package twyla;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends Page {

    private final String USERNAME_ID = "userName";
    private WebElement userNameElement;

    private final String LOGIN_ID = "enter";
    private WebElement enterElement;

    private final String ROUTES_ID = "routes";
    private final String A_TAG_ID = "a";

    public HomePage() {
        super();
        openPage(BASE_URL);
        initLoginPage();
    }

    public void initLoginPage() {
        Page.pause(2000);
        setUserNameElement(driver.findElement(By.id(USERNAME_ID)));
        setEnterElement(driver.findElement(By.id(LOGIN_ID)));

    }

    public WebElement getUserNameElement() {
        return userNameElement;
    }

    public void setUserNameElement(WebElement userNameElement) {
        this.userNameElement = userNameElement;
    }

    public void setUserName(String userName) {

        getUserNameElement().clear();
        getUserNameElement().sendKeys(userName);
    }

    public WebElement getEnterElement() {
        return enterElement;
    }

    public void setEnterElement(WebElement enterElement) {
        this.enterElement = enterElement;
    }

    public List<WebElement> getRouteElements() {
        return driver.findElement(By.id(ROUTES_ID)).findElements(By.tagName(A_TAG_ID));
    }
}
