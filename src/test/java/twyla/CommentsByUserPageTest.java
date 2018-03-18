package twyla;

import org.junit.*;
import org.openqa.selenium.WebElement;

public class CommentsByUserPageTest {

    private CommentsByUserPage commentsByUserPage;

    private static final String USER = "admin";

    @BeforeClass
    public static void beforeClass() throws Exception {
        Page.initializeDriver();
    }

    @Before
    public void setUp() throws Exception {
        commentsByUserPage = new CommentsByUserPage(USER);
    }

    @Test
    public void testLabels() throws Exception {
        Assert.assertEquals(1, commentsByUserPage.getLabels().size());
        for (WebElement element : commentsByUserPage.getLabels()) {
            Assert.assertTrue(element.getText().equals("My Comments"));
        }

    }

    @Test
    public void testHeaders() throws Exception {
        String headers = commentsByUserPage.getHeaders().getText();
        Assert.assertTrue(headers.contains("Book ID"));
        Assert.assertTrue(headers.contains("Title"));
        Assert.assertTrue(headers.contains("Comment"));
        Assert.assertTrue(headers.contains("Rating"));

    }

    @AfterClass
    public static void tearDown() {
        Page.quitDriver();
    }
}
