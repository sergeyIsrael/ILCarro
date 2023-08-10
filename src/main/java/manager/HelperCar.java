package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperCar extends HelperBase {

    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm(){
        pause(5000);
//        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        click(By.xpath("//a[.=' Let the car work ']"));

    }

    public void fillCarForm(Car car){
        if(isCarFormPresent()==false) return;
        typeLocation(car.getLocation());
        type(By.id("make"), car.getMake());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), car.getSeats());
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
//        clickSerialNumber(car.getCarRegNumber());
        type(By.id("price"), car.getPrice());
    }

    public void fillCarFormCoordinate(Car car){
        if(isCarFormPresent()==false) return;
        typeLocation(car.getLocation());
        type(By.id("make"), car.getMake());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), car.getSeats());
        type(By.id("class"), car.getCarClass());
//        type(By.id("serialNumber"), car.getCarRegNumber());
        clickSerialNumber(car.getCarRegNumber());
        type(By.id("price"), car.getPrice());
    }


    public void typeLocation(String address){
        type(By.id("pickUpPlace"), address);
        click(By.cssSelector("div.pac-item"));
    }

    public void clickSerialNumber(String text){
        WebElement element = wd.findElement(By.id("serialNumber"));
        Actions actions = new Actions(wd);
        actions.moveToElement(element).click().perform();
        element.clear();
        element.sendKeys(text);
    }

    public void select(By locator, String option){
        new Select(wd.findElement(locator)).selectByValue(option);
    }

    public boolean isCarFormPresent(){
        return new WebDriverWait(wd, 10)
                .until(ExpectedConditions
                        .textToBePresentInElement(wd.findElement(By.cssSelector("h2")),
                                "details"));
    }




}
