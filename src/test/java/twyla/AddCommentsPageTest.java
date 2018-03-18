package twyla;

import org.junit.*;
import org.openqa.selenium.WebElement;

public class AddCommentsPageTest {

    private AddCommentsPage addCommentsPage;

    private static final String USER = "admin";

    @BeforeClass
    public static void beforeClass() throws Exception {
        Page.initializeDriver();
    }

    @Before
    public void setUp() throws Exception {
        addCommentsPage = new AddCommentsPage(USER);
    }

    @Test
    public void testLabels() throws Exception {
        Assert.assertEquals(2, addCommentsPage.getLabels().size());
        for (WebElement element : addCommentsPage.getLabels()) {
            Assert.assertTrue(element.getText().equals("Add Your comments")
                || element.getText().equals("Your rating"));
        }

    }

    @Test
    public void testTextAreaComment() {
        String comment = "Good";
        addCommentsPage.setTextAreaComment(comment);
        Assert.assertEquals(comment,
            addCommentsPage.getTextAreaComment().getAttribute("value"));
    }

    @Test
    public void testSelectRating() {
        String rating = "4";
        addCommentsPage.setSelectBoxRating("4");
        WebElement option = addCommentsPage.getSelectedItem().getFirstSelectedOption();
        String selectedValue = option.getText();
        Assert.assertEquals(rating,
            selectedValue);
    }

    @Test
    public void testHeaders() throws Exception {
        String headers = addCommentsPage.getHeaders().getText();
        Assert.assertTrue(headers.contains("Rating"));
        Assert.assertTrue(headers.contains("Comment"));
        Assert.assertTrue(headers.contains("User"));

    }

    @Test
    public void testAddComment() throws Exception {
        try {
            int sizeBeforeAdding = addCommentsPage.getComments().size();
            addCommentsPage.addComment("Good", "5");
            Page.pause(2000);
            int sizeAfterAdding = addCommentsPage.getComments().size();
            Assert.assertTrue(sizeAfterAdding > sizeBeforeAdding);
        } finally {
            //TODO Delete the book, this is for test only
        }

    }

    @AfterClass
    public static void tearDown() {
        Page.quitDriver();
    }

}
