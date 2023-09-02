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
            Assertions.assertNotNull(info.getText(), "В информации нет текста");
        }
        Assertions.assertNotNull(nameOfCourse.getText(), "В заголовке нет текста");
        Assertions.assertNotNull(descriptionOfCourse.getText(), "В описании нет текста");
    }

}
