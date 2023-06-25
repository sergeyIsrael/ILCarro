import models.Car;
import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCar extends TestBase{

    @BeforeMethod
    public void preconditions(){
if(app.getUser().isLoggedIn() == false)
    app.getUser().login(
            new User().withEmail("main@gmail.com").withPassword("123asd$A"));
    }


    @Test
    public void addNewCarPositive(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Car car = Car.builder()
                .location("Tel Aviv")
                .make("KIA")
                .model("Sportage")
                .year("2023")
                .fuel("Petrol")
                .seats("5")
                .carClass("B")
                .carRegNumber("100-200-" + i)
                .price("150")
                .about("")
                .build();
        app.getCar().openCarForm();
        app.getCar().fillCarForm(car);
        app.getUser().submitLogin();
    }







}
