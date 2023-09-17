package pageobject;

import data.events.EventsTypeData;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class EventsPage extends AbsPageObject {
    public EventsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(@class, 'dod_new-event__time')][1]")
    List<WebElement> listOfDates;

    @FindBy(xpath = "(//div[contains(@class, 'dod_new-events-dropdown__input')])[1]")
    WebElement eventTypeSelector;

    @FindBy(xpath = "//div[contains(@class, 'dod_new-type__text')][1]")
    List<WebElement> listOfEventsTypes;

    public void openEventsPage() {
        driver.get(System.getProperty("base.url") + "/events/near/");
    }

    public void checkDates() throws ParseException {
        Assertions.assertNotEquals(0, listOfDates.size(), "На странице нет ни одной карточки");
        SimpleDateFormat formatterForYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatterForCard = new SimpleDateFormat("dd MM yyyy");
        Date date = new Date(System.currentTimeMillis());
        String year = formatterForYear.format(date);
        String currentDateString = formatterForCard.format(date);
        List<Date> dates = new ArrayList<>();
        String datesForList = null;
        Date currentDate = formatterForCard.parse(currentDateString);
        log.info("Сумма курсов для теста: {}", listOfDates.size());
        for (WebElement element : listOfDates) {
            datesForList = String.format("%s %s", element.getText(), year);
            dates.add(formatterForCard.parse(datesForList));
        }
        boolean result;
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).getTime() >= currentDate.getTime()) {
                result = true;
            } else {
                result = false;
                log.debug("Проблема со следующей датой: {}", dates.get(i));
            }
            Assertions.assertTrue(result, String.format("Неверная дата в карточке # %s", i + 1));
        }
    }

    public void downToThePage() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(200);
        }
        Thread.sleep(300);
    }

    public void openEventsTypeSelector() {
        eventTypeSelector.click();
    }

    @SneakyThrows
    public void selectEventType(EventsTypeData eventsType) {
      String element = new String("(//a[contains(@title, '%s')])[1]".getBytes(), eventsType.getName());
        driver.findElement(By.xpath(element)).click();
    }


    public void checkEventType(EventsTypeData eventsTypeShouldBe) {
        Assertions.assertNotEquals(0, listOfEventsTypes.size(), "На странице нет ни одной карточки");
        for (WebElement element : listOfEventsTypes) {
            Assertions.assertEquals(eventsTypeShouldBe.getName(), element.getText(), "Ошибка в типе события");
        }
    }

}
