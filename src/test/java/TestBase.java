import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

//   для чтения из terminal
//   "browser" - меняет на тот что мы укажем
//   BrowserType.CHROME - это значение по умолчанию, если не укажем browser
//   getProperty - получи свойство,
//   а даёт его нам build.gradle, там есть systemProperty 'browser', '$browser'
    static ApplicationManager app = new ApplicationManager(
            System.getProperty("browser", BrowserType.CHROME)
    );

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void stop(){
        app.tearDown();
    }

    @BeforeMethod(alwaysRun = true)
    public void startLogger(Method method){
        logger.info("Method " + method.getName() + " is started");
    }

    @AfterMethod(alwaysRun = true)
    public  void end(){
        logger.info("================================");
    }




}
