package twyla;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AllOtherBooksPageTest {

    private static AllOtherBooksPage allOtherBooksPage;

    private static final String USER = "admin";

    @BeforeClass
    public static void beforeClass() throws Exception {
        Page.initializeDriver();
    }

    @Before
    public void setUp() throws Exception {
        allOtherBooksPage = new AllOtherBooksPage(USER);
    }

    @Test
    public void testLabels() throws Exception {
        Assert.assertEquals(1, allOtherBooksPage.getLabels().size());
        for (WebElement element : allOtherBooksPage.getLabels()) {
            Assert.assertTrue(element.getText().equals("All Other Books"));
        }

    }

    @Test
    public void testHeaders() throws Exception {
        String headers = allOtherBooksPage.getHeaders().getText();
        Assert.assertTrue(headers.contains("ISBN ID"));
        Assert.assertTrue(headers.contains("Title"));
        Assert.assertTrue(headers.contains("User"));

    }

    @Test
    public void testBooksClick() throws Exception {

        if (allOtherBooksPage.getBooks().size() == 0) {

            MyBooksPage page = new MyBooksPage("testUser");
            page.addBook(page.getRandomID(), "Test title");
            Page.pause(3000);
        }

        allOtherBooksPage = new AllOtherBooksPage(USER);
        Page.pause(2000);
        allOtherBooksPage.getBooks().get(0).findElement(By.tagName("a")).click();
    }

    @AfterClass
    public static void tearDown() {
        Page.quitDriver();
    }
}
