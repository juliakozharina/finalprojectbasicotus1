package components;

import data.CoursesData;
import data.MainMenuItemsData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainMenuComponent extends AbsBaseComponent {

    public MainMenuComponent(WebDriver driver) {
        super(driver);
    }

    private String mainMenuLocator = "//*[@id=\"__next\"]/div[1]/main/section[1]/section/div/div/div[10]";

    public void clickMainMenuSection(MainMenuItemsData mainMenuCategory, String section) {
        String locator = String.format(mainMenuLocator, mainMenuCategory.getName());

        String courseLocator = String.format(
                "//header[contains(@class, 'header2')]//a[contains(text(), '%s')]",
                section
        );
        actions.moveToElement(driver.findElement(By.xpath(locator)))
                .click(driver.findElement(By.xpath(courseLocator))).build().perform();
    }

}
