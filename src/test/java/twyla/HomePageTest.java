package twyla;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePageTest {

    private static HomePage homePage;

    @BeforeClass
    public static void setUp() throws Exception {
        Page.initializeDriver();
        homePage = new HomePage();
    }

    @Test
    public void testTitle() throws Exception {
        Assert.assertEquals("BooksRepo", homePage.getTitle());
    }

    @Test
    public void testUsernameField() {
        String userName = "someUser";
        homePage.setUserName(userName);
        Assert.assertEquals(userName,
            homePage.getUserNameElement().getAttribute("value"));
    }

    @Test
    public void testEmptyUserId() {
        String userName = " ";
        homePage.setUserName(userName);
        homePage.getEnterElement().click();
        Alert alert = HomePage.driver.switchTo().alert();
        Assert.assertEquals("user cannot be empty", alert.getText());
        alert.accept();

    }

    @Test
    public void testSuccessfulLogin() {
        String userName = "newUser";
        homePage.setUserName(userName);
        homePage.getEnterElement().click();
        HomePage.pause(50);
        List<WebElement> routes = homePage.getRouteElements();
        Assert.assertEquals(3, routes.size());
        String text;
        for (WebElement element: routes) {
            text = element.getText();
            Assert.assertTrue(text.equals("My Books") || text.equals("My comments") || text.equals("All Other Books"));
        }
    }

    @AfterClass
    public static void tearDown() {
        Page.quitDriver();
    }

}
