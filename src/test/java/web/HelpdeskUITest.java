package web;

import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private WebDriver driver;
    private Ticket ticket;
    @BeforeClass
    public void setup() throws IOException {
        loadProperties();
        setupDriver();
    }

    @Step("Загрузить конфигурационные файлы")
    private void loadProperties() throws IOException {
        // Читаем конфигурационные файлы в System.properties
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
    }

    @Step("Создать экземпляр драйвера")
    private void setupDriver() {
        System.setProperty("webdriver.chrome","webdriver.chrome.driver");
        // Создание экземпляра драйвера
        driver = new ChromeDriver();
        // Устанавливаем размер окна браузера, как максимально возможный
        driver.manage().window().maximize();
        // Установим время ожидания для поиска элементов
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Установить созданный драйвер для поиска в веб-страницах
        AbstractPage.setDriver(driver);
    }

    @Test //Выполнение тест-кейса(шаги)
    public void createTicketTest() {
        //Шаг 1. Открытие страницы создания тикетов из главного меню
        openNewTicketsPage();
        //Шаг 2. Создание тикета
        ticket = buildNewTicket();
        new CreateTicketPage().createTicket(ticket);
        //сохранение и проверка введенных данных тикета
        ViewPage viewPage = new ViewPage();
        viewPage.saveId(ticket);
        viewPage.checkTicket(ticket);
        //Шаг 3-4. Переход на вкладку авторизации и авторизация
        authentificationWithUsernameAdmin();
        //Шаг 5. Поиск тикета по имени
        searchTicketForTitle();
        //Шаг 6. Открытие нужного тикета по id созданного тикета
        new TicketsPage().openTicket(ticket);
        //Сверка соответствия с введенными данными
        new TicketPage().checkTicket(ticket);
    }


    private Ticket buildNewTicket() {
        Ticket ticket = new Ticket();
        ticket.setSubmitter_email("wolfAUF@mail.ru");
        ticket.setDue_date(LocalDateTime.now());
        ticket.setDescription("Бросай табак кури кое что");
        ticket.setPriority(2);
        ticket.setQueue(1);
        ticket.setTitle("Это могла бы быть ваша реклама");
        return ticket;
    }

    private void openNewTicketsPage(){
        String URL = System.getProperty("site.url");
        driver.get(URL);
        //Переход на страницу создания тикета
        driver.findElement(By.xpath("//a[@href='/tickets/submit/']")).click();
    }
    private void searchTicketForTitle(){
        //Ввод названия тикета в поисковую строку
        driver.findElement(By.xpath("//input[@aria-describedby='basic-addon2']")).sendKeys(ticket.getTitle());
        //Нажатие на кнопку поиска
        driver.findElement(By.xpath("//div[@class='input-group-append']")).click();
    }

    private void authentificationWithUsernameAdmin(){
        String username = System.getProperty("user");
        String password = System.getProperty("password");
        //Нажатие на кнопку "Log in"
        driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle']")).click();
        new LoginPage().login(username, password);
    }

    @AfterTest
    public void close() {
        if (driver != null) {

            // Закрываем одно текущее окно браузера
            driver.close();
            // Закрываем все открытые окна браузера, завершаем работу браузера, освобождаем ресурсы
            driver.quit();
        }
    }
}
