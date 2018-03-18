package twyla;

import org.openqa.selenium.Cookie;

public class CommentsByUserPage extends CommentsPage {

    public CommentsByUserPage(String user) {
        super();
        String url = getBaseUrl() + "/commentsByUser";
        Cookie cookie = new Cookie("user", user);
        openPage(url);
        driver.manage().addCookie(cookie);
        Page.refresh();
    }

}
