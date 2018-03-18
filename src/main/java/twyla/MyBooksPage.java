package twyla;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyBooksPage extends BooksPage {

    public MyBooksPage(String user) {
        super("/books", user);
    }

    public WebElement getTextBoxISBNID() {
        return driver.findElement(By.id("isbnID"));
    }

    public WebElement getTextBoxTitle() {
        return driver.findElement(By.id("title"));
    }

    public WebElement getButtonAdd() {
        return driver.findElement(By.id("add"));
    }

    public void setTextBoxISBNID(String value) {

        getTextBoxISBNID().clear();
        getTextBoxISBNID().sendKeys(value);
    }

    public void setTextBoxTitle(String value) {

        getTextBoxTitle().clear();
        getTextBoxTitle().sendKeys(value);
    }

    public void addBook(String id, String title) {

        setTextBoxISBNID(id);
        setTextBoxTitle(title);
        getButtonAdd().click();
    }

}
