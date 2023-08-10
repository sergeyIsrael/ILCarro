import org.testng.annotations.Test;

public class SearchTests extends TestBase {

@Test (groups = {"regression", "positive"})
    public void searchPositiveTest(){
    app.getSearch().fillSearchForm("Tel Aviv", "12/10/2023", "05/12/2024");
    app.getSearch().submitForm();
}



}
