package pageobject;

import org.openqa.selenium.WebDriver;

public class CoursesTestingPage extends AbsPageObject {
    public CoursesTestingPage(WebDriver driver) {
        super(driver);
    }

    public void openTestingCoursePage(){
        driver.get(System.getProperty("base.url")+"/categories/testing/");
    }
}
