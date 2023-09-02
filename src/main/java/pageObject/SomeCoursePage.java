package pageObject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SomeCoursePage extends AbsPageObject {
    public SomeCoursePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[contains(@class, 'sc-10wn8wt-0')]")
    private List<WebElement> infoAboutCoursesList;

    @FindBy(xpath = "(//h1[contains(@class, 'sc-')])[1]")
    WebElement nameOfCourse;

    @FindBy(xpath = "((//h1[contains(@class, 'sc-')])[1]/../div)[2]")
    WebElement descriptionOfCourse;


    public void checkInfoAboutCourse() {
        for (WebElement info : this.infoAboutCoursesList) {
            Assertions.assertNotNull(info.getText(), "There is no text in info");
        }
        Assertions.assertNotNull(nameOfCourse.getText(), "There is no text in title");
        Assertions.assertNotNull(descriptionOfCourse.getText(), "There is no text in description");
    }

}
