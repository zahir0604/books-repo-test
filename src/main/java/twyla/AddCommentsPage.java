package twyla;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddCommentsPage extends CommentsPage {

    private MyBooksPage booksPage;

    public AddCommentsPage(String user) {

        super();
        booksPage = new MyBooksPage("admin");
        if (booksPage.getBooks().size() == 0) {
            booksPage.addBook(booksPage.getRandomID(), "Test Title");
            pause(2000);
        }
        booksPage.getBooks().get(0).findElement(By.tagName("a")).click();
        pause(2000);
    }

    public WebElement getTextAreaComment() {
        return driver.findElement(By.id("comment"));
    }

    public WebElement getSelectBoxRating() {
        return driver.findElement(By.id("rating"));
    }

    public WebElement getButtonAdd() {
        return driver.findElement(By.id("add"));
    }

    public void setTextAreaComment(String value) {

        getTextAreaComment().clear();
        getTextAreaComment().sendKeys(value);
    }

    public void setSelectBoxRating(String value) {

        Select dropdown = new Select(getSelectBoxRating());
        dropdown.selectByValue(value);
    }

    public void addComment(String Comment, String rating) {

        setTextAreaComment(Comment);
        setSelectBoxRating(rating);
        getButtonAdd().click();
    }

    public Select getSelectedItem(){
        return new Select(getSelectBoxRating());
    }
}
