import manager.TestNgListener;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)


public class RegistrationTestsNegative extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLoggedIn()) app.getUser().logout();
    }

    @Test
    public void registrationNegativeWrongEmail(){
        User user = new User()
                .withName("Sergei")
                .withLastName("Orlov")
                .withEmail("mail.com")
                .withPassword("$Asdf1234");
        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//*[text()='Wrong email format']")));
    }


    @Test
    public void registrationNegativeWrongPassword(){
        User user = new User()
                .withName("Sergei")
                .withLastName("Orlov")
                .withEmail("mail@1.com")
                .withPassword("1234");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        Assert.assertTrue(app.getUser().isElementPresent
                (By.xpath("//div[contains (text(), 'Password must contain 1')]" )));
    }


    @Test
    public void registrationNegativeNameIsEmpty(){
        User user = new User()
                .withName("")
                .withLastName("Orlov")
                .withEmail("mail@1.com")
                .withPassword("123456@Aa");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        Assert.assertTrue(app.getUser().isElementPresent
                (By.xpath("//*[text()=' Name is required ']" )));
    }


    @Test
    public void registrationNegativeLastNameIsEmpty(){
        User user = new User()
                .withName("Sergei")
                .withLastName("")
                .withEmail("mail@1.com")
                .withPassword("123456@Aa");

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        Assert.assertTrue(app.getUser().isElementPresent
                (By.xpath("//*[text()=' Last name is required ']" )));
    }




}
