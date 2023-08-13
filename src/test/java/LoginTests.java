import manager.ProviderData;
import manager.TestNgListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)


public class LoginTests extends TestBase {

    @BeforeMethod (alwaysRun = true)
    public void precondition(){
    if (app.getUser().isLoggedIn())
    app.getUser().logout();
    }

    @Test (groups = {"smoke","positive"})
    public void loginPositive(){
    app.getUser().openLoginForm();
    app.getUser().fillLoginForm(
            "main@gmail.com", "123asd$A");
    app.getUser().submitLogin();
    Assert.assertTrue(app.getUser().isLoggedSuccess());
}

    @Test (groups = {"smoke","positive","prod"})
    public void loginPositiveProps(){
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(
                app.getEmail(), app.getPassword());
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }


    @Test (groups = {"smoke","positive"})
    public void loginPositiveUser(){
        User user = new User()
                .withEmail("asd@fgh.com")
                .withPassword("$Asdf1234");
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user.getEmail(), user.getPassword());
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @Test (groups = {"regression"})
    public void loginPositiveUserData(){
    User user = new User();
        user.withEmail("main@gmail.com")
            .withPassword("123asd$A");
    app.getUser().openLoginForm();
    app.getUser().fillLoginForm(user);
    app.getUser().submitLogin();
    }

//    код ниже означает: под функцию dataProvider (встроенная java функция), бери метод userDto,
//    в классе ProviderData
//    класс ProviderData нельзя назвать DataProvider т.к. DataProvider это функ. java
//    переставил слова и уже всё в порядке))
//    DataTransferObject - DTO. В данном случае описывается структура данных, объект для передачи данных.
    @Test(dataProvider = "userDto", dataProviderClass = ProviderData.class)
    public void loginPositiveUserDTO(User user){
//        User user = new User()
//                .withEmail("asd@fgh.com")
//                .withPassword("$Asdf1234")
//                ;
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @AfterMethod (alwaysRun = true)
    public void postCondition(){
    app.getUser().clickOkButton();
}


}
