package components;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LessonsCardsComponent extends AbsBaseComponent {
    public LessonsCardsComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class, 'lessons__page')]//a[@href]")
    private List<WebElement> coursesCards;

    public void checkAmountOfCourses(int coursesShouldBe) {
        Assertions.assertEquals(coursesShouldBe, coursesCards.size());
    }

    public void clickSomeCard(int numberOfCard) {
        coursesCards.get(numberOfCard).click();
    }

}
