package twyla;

import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyBooksPageTest {

    private MyBooksPage myBooksPage;

    private static final String USER = "admin";

    @BeforeClass
    public static void beforeClass() throws Exception {
        Page.initializeDriver();
    }

    @Before
    public void setUp() throws Exception {
        myBooksPage = new MyBooksPage(USER);
    }

    @Test
    public void testLabels() throws Exception {
        Assert.assertEquals(2, myBooksPage.getLabels().size());
        for (WebElement element : myBooksPage.getLabels()) {
            Assert.assertTrue(element.getText().equals("Add a Book")
                || element.getText().equals("My Books"));
        }

    }

    @Test
    public void testTextBoxISBNID() {
        String isbnId = "001";
        myBooksPage.setTextBoxISBNID(isbnId);
        Assert.assertEquals(isbnId,
            myBooksPage.getTextBoxISBNID().getAttribute("value"));
    }

    @Test
    public void testTextBoxTitle() {
        String title = "Head First Java";
        myBooksPage.setTextBoxTitle(title);
        Assert.assertEquals(title,
            myBooksPage.getTextBoxTitle().getAttribute("value"));
    }

    @Test
    public void testHeaders() throws Exception {
        String headers = myBooksPage.getHeaders().getText();
        Assert.assertTrue(headers.contains("ISBN ID"));
        Assert.assertTrue(headers.contains("Title"));

    }

    @Test
    public void testAddBook() throws Exception {
        try {
            int sizeBeforeAdding = myBooksPage.getBooks().size();
            myBooksPage.addBook(myBooksPage.getRandomID(), "Head First Java");
            Page.pause(2000);
            int sizeAfterAdding = myBooksPage.getBooks().size();
            Assert.assertTrue(sizeAfterAdding > sizeBeforeAdding);
        } finally {
            //TODO Delete the book, this is for test only
        }

    }

    @Test
    public void testCannotAddSameBookAgain() throws Exception {

        try {
            String id = myBooksPage.getRandomID();
            myBooksPage.addBook(id, "Head First Java");

            Page.pause(2000);
            myBooksPage = new MyBooksPage(USER);
            myBooksPage.addBook(id, "Head First Java");
            Page.pause(2000);
            Alert alert = HomePage.driver.switchTo().alert();
            Assert.assertEquals("Book already exists", alert.getText());
            alert.accept();
        } finally {

            //TODO Delete the book, this is for test only
        }

    }

    @Test
    public void testBooksClick() throws Exception {

        if (myBooksPage.getBooks().size() == 0) {

            myBooksPage.addBook(myBooksPage.getRandomID(), "New Title");
        }

        myBooksPage.getBooks().get(0).findElement(By.tagName("a")).click();
    }

    @AfterClass
    public static void tearDown() {
        Page.quitDriver();
    }
}
