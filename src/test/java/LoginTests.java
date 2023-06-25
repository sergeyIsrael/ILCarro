import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {


@BeforeMethod
public void precondition(){
if (app.getUser().isLoggedIn()){
    app.getUser().logout();
}
}

@Test
    public void loginPositive(){
    app.getUser().openLoginForm();
    app.getUser().fillLoginForm(
            "main@gmail.com", "123asd$A");
    app.getUser().submitLogin();
    Assert.assertTrue(app.getUser().isLoggedSuccess());
}

@Test
    public void loginPositiveUserData(){

    User user = new User();
        user.withEmail("main@gmail.com")
            .withPassword("123asd$A");
    app.getUser().openLoginForm();
    app.getUser().fillLoginForm(user);
    app.getUser().submitLogin();
}

@AfterMethod
    public void postCondition(){
    app.getUser().clickOkButton();
}


}
