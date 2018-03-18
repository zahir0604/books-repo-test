package twyla;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BooksPage extends Page{

    public BooksPage(String url, String user) {
        super();
        url = BASE_URL + url;
        Cookie cookie = new Cookie("user", user);
        openPage(url);
        driver.manage().addCookie(cookie);
        Page.refresh();
    }

    protected List<WebElement> getLabels() {
        return driver.findElements(By.tagName("h2"));
    }

    protected WebElement getHeaders() {
        return driver.findElement(By.id("booksHeader"));
    }


    protected List<WebElement> getBooks() {
        return driver.findElements(By.id("booksList"));
    }

    protected String getRandomID() {
        int id = (int) (Math.random() * 1000);
        return String.valueOf(id);
    }
}
