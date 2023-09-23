import components.LessonsCardsComponent;
import components.MainMenuComponent;
import data.CoursesData;
import data.events.EventsData;
import data.events.EventsTypeData;
import data.MainMenuItemsData;
import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pageobject.*;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static driver.DriverFactory.setDriverName;


@Slf4j
public class OtusTest {
    private WebDriver driver;
    protected String path = "/";


    @SneakyThrows
    @BeforeAll
    public static void install(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() throws DriverNotSupportedException {
        List<String> option = new ArrayList<>();
        option.add("--window-size=1920,1080");
        driver = new DriverFactory().create(setDriverName(), option);
        log.info("Test started in {} browser",setDriverName());
 //       driver = new ChromeDriver();
    }

    @AfterEach
    public void setDown(){
        if (this.driver != null){
            this.driver.close();
            this.driver.quit();
        }
    }


//1.	Проверка количества курсов в разделе тестирование
    @Test
    public void checkAmountOfCoursesInTestSection() {
        new DriverFactory().setImplicitlyWait(driver);
        new MainPage(driver).openMainPage();
        new MainMenuComponent(driver).clickMainMenuSection(MainMenuItemsData.Courses, CoursesData.Testing.getName());
        new LessonsCardsComponent(driver).checkAmountOfCourses(12);
    }
//2.	Просмотр карточки курса
    @Test
    public void checkCardOfCourse() {
        new DriverFactory().setImplicitlyWait(driver);
        new CoursesTestingPage(driver).openTestingCoursePage();
        new LessonsCardsComponent(driver).clickSomeCard(1);
        new SomeCoursePage(driver).checkInfoAboutCourse();
    }
//3.	Валидация дат предстоящих мероприятий
    @Test
    public void checkDatesForFutureEvents() throws InterruptedException, ParseException {
        new DriverFactory().setImplicitlyWait(driver);
        new MainPage(driver).openMainPage();
        new MainMenuComponent(driver).clickMainMenuSection(MainMenuItemsData.Events, EventsData.CalendarOfEvents.getName());
        EventsPage eventsPage = new EventsPage(driver);
        eventsPage.downToThePage();
        eventsPage.checkDates();
    }
//4.	Просмотр мероприятий по типу
    @Test
    public void checkByEventsType() throws InterruptedException {
        new DriverFactory().setImplicitlyWait(driver);
        EventsPage eventsPage = new EventsPage(driver);
        eventsPage.openEventsPage();
        eventsPage.openEventsTypeSelector();
        eventsPage.selectEventType(EventsTypeData.OpenWebinars);
        eventsPage.downToThePage();
        eventsPage.checkEventType(EventsTypeData.OpenWebinars);
    }

}
