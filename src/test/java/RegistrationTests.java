import manager.TestNgListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)


public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLoggedIn()) app.getUser().logout();
    }


    @Test
    public void registrationPositive(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("John")
                .withLastName("Snow")
                .withEmail("john_" + i + "@mail.com")
                .withPassword("$Asdf1234");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().submitLogin();
        logger.info("registrationPositive starts with credentials: login: "
                + user.getEmail() + " & password: " + user.getPassword() );
        Assert.assertTrue(app.getUser().isLoggedSuccess());

    }


    @AfterMethod
    public void postcondition(){
        app.getUser().clickOkButton();
    }



}
