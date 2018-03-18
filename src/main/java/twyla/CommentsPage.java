package twyla;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommentsPage extends Page{

    public CommentsPage() {
        super();
    }

    protected List<WebElement> getLabels() {
        return driver.findElements(By.tagName("h2"));
    }

    protected WebElement getHeaders() {
        return driver.findElement(By.id("commentsHeader"));
    }


    protected List<WebElement> getComments() {
        return driver.findElements(By.id("commentsList"));
    }
}
