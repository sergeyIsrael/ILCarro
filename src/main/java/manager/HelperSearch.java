package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase {

    public HelperSearch(WebDriver wd) {
        super(wd);
    }


    public void fillSearchForm(String city, String dateFrom, String dateTo){
        fillCity(city);
//        selectPeriodDays(dateFrom, dateTo);
//        selectPeriodDaysDatePicker(dateFrom, dateTo);
//        selectPeriodMonthsDatePicker(dateFrom, dateTo);
        selectPeriodYearsDatePicker(dateFrom, dateTo);
    }


    public void fillCity(String city){
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }

    public void submitForm(){
        click(By.xpath("//button[@type='submit']"));
    }

    public void selectPeriodDays(String dateFrom, String dateTo){
//    9/13/2023 - 9/20/2023
        type(By.id("dates"),dateFrom + " - " + dateTo);
        click(By.id("city"));
        pause(3000);
    }

    public void selectPeriodDaysDatePicker(String dateFrom, String dateTo){
        String[] startDate = dateFrom.split("/");
        String[] endDate = dateTo.split("/");
//        9/13/2023
//  index 0  1  2
        click(By.id("dates"));
        pause(2000);
//        v1
        click(By.xpath(" //div[.=' " + startDate[1] + " '] ")); //div[.=' 13 ']
        pause(2000);
        click(By.xpath(" //div[.=' " + endDate[1] + " '] "));
        pause(3000);
//        v2
//        String locatorStartDate = String.format("//div[.=' %s ']", startDate[1]);
//        String locatorEndDate = String.format("//div[.=' %s ']", endDate[1]);
//        click(By.xpath(locatorStartDate));
//        click(By.xpath(locatorEndDate));
    }

    public void selectPeriodMonthsDatePicker(String dateFrom, String dateTo){
        //        9/13/2023
        //  index 0  1  2
        int fromNowToStart = 0;
        int fromStartToEnd = 0;
        String[] startDate = dateFrom.split("/");
        String[] endDate = dateTo.split("/");

        click(By.id("dates"));

        fromStartToEnd = Integer.parseInt(endDate[0]) - Integer.parseInt(startDate[0]);
        if(LocalDate.now().getMonthValue() != Integer.parseInt(startDate[0])){
            fromNowToStart = Integer.parseInt(startDate[0]) - LocalDate.now().getMonthValue();
        }
//        choose start month
        for (int i = 0; i < fromNowToStart; i++){
            click(By.xpath("//button[@aria-label='Next month']"));
        }
//        click start date
        click(By.xpath(" //div[.=' " + startDate[1] + " '] ")); //div[.=' 13 ']
//        choose end month
        for (int i = 0; i < fromStartToEnd; i++) {
            click(By.xpath("//button[@aria-label='Next month']"));
        }
//        click end date
        click(By.xpath(" //div[.=' " + endDate[1] + " '] "));
        pause(2000);
    }

    public void selectPeriodYearsDatePicker(String dateFrom, String dateTo){
        LocalDate startDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate endDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate nowDate = LocalDate.now();
        String locatorStartDate = String.format("//div[.=' %s ']", startDate.getDayOfMonth());
        String locatorEndDate = String.format("//div[.=' %s ']", endDate.getDayOfMonth());
        click(By.id("dates"));

//        Выбор Старт аренды месяца, если разные года
//        Пример: сейчас июль 7 мес., Начало аренды март 3 мес. след. года:
//        12 - 7 + 3. Получим количество кликов до нужного месяца.
        int startToEndMonth = startDate.getYear() - nowDate.getYear() == 0 ?
//                if the same year
                startDate.getMonthValue() - nowDate.getMonthValue() :
//                if different years
                12 - nowDate.getMonthValue() + startDate.getMonthValue();

        //        choose start month
        for (int i = 0; i < startToEndMonth; i++){
            click(By.xpath("//button[@aria-label='Next month']"));
            pause(1000);
        }

        click(By.xpath(locatorStartDate));

//              Если года конца и началa аренды Разные or ==
        startToEndMonth = endDate.getYear() - startDate.getYear() == 0 ?
//                if the same year
                endDate.getMonthValue() - startDate.getMonthValue() :
//                if different years
                12 - startDate.getMonthValue() + endDate.getMonthValue();

        //        choose end month
        for (int i = 0; i < startToEndMonth; i++){
            click(By.xpath("//button[@aria-label='Next month']"));
            pause(1000);
        }

        click(By.xpath(locatorEndDate));
    }




}
