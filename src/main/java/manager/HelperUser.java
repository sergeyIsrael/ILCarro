package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm(){
        wd.findElement(By.xpath("//*[.=' Log in ']")).click();
    }

    public void fillLoginForm(String email, String password){
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }
// overloading
    public void fillLoginForm(User user){
        type(By.xpath("//input[@id='email']"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword());
    }

    public void submitLogin(){
        click(By.xpath("//button[@type='submit']"));
//        like the same
//        wd.findElement(By.xpath("//button[@type='submit']")).submit();
//        inside method click
//        wd.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void logout(){
click(By.xpath("//a[.=' Logout ']"));
    }

    public boolean isLoggedIn(){
return isElementPresent(By.xpath("//a[.=' Logout ']"));
    }

    public void clickOkButton(){
        click(By.xpath("//button[@type='button']"));
}

public boolean isLoggedSuccess(){
        return isElementPresent(By.xpath("//h2[contains(text(),'success')]"));
}

public void login(User user){
        openLoginForm();
        fillLoginForm(user);
        submitLogin();
        clickOkButton();
}

public void openRegistrationForm(){
        wd.findElement(By.xpath("//*[.=' Sign up ']")).click();
    }

    public void fillRegistrationForm(User user){
        type(By.xpath("//input[@id='name']"), user.getName());
        type(By.xpath("//input[@id='lastName']"), user.getLastName());
        type(By.xpath("//input[@id='email']"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword());
        click(By.cssSelector("label[for='terms-of-use']"));
    }



}
