package pages;

import io.qameta.allure.Step;
import models.Dictionaries;
import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/** Страница отдельного тикета (авторизированный пользователь) */
public class TicketPage extends HelpdeskBasePage {

    /* Верстка страницы может измениться, поэтому для таблиц вместо индексов строк и столбцов лучше использовать
       более универсальные локаторы, например поиск по тексту + parent, following-sibling и другие.

       Текст тоже может измениться, но в этом случае элемент не будет найден и тест упадет,
       а ошибку можно будет легко локализовать и исправить.
       В случае изменений ячеек таблицы, локатор будет продолжать работать, но будет указывать на другой элемент,
       поведение теста при этом изменится непредсказуемым образом и ошибку будет сложно найти. */
    private WebElement dueDate = driver.findElement(By.xpath("//th[text()='Due Date']/following-sibling::td[1]"));

    // todo: проинициализировать элементы через driver.findElement
    private WebElement title = driver.findElement(By.xpath("//th[@colspan='4']//h3"));
    private WebElement queue = driver.findElement(By.xpath("//th[@colspan='4' and contains(text(),'Queue:')]"));
    private WebElement email = driver.findElement(By.xpath("//th[text() = 'Submitter E-Mail']/following-sibling::td[1]"));
    private WebElement priority = driver.findElement(By.xpath("//th[text() = 'Priority']/following-sibling::td[1]"));
    private WebElement description = driver.findElement(By.xpath("//td[@id='ticket-description']//p"));

    @Step("Проверить значение полей на странице тикета")
    public void checkTicket(Ticket ticket) {
        Assert.assertTrue(title.getText().contains(ticket.getTitle()));
        Assert.assertTrue(queue.getText().contains(Dictionaries.getQueue(ticket.getQueue())));
        Assert.assertTrue(email.getText().contains(ticket.getSubmitter_email()));
        Assert.assertTrue(priority.getText().contains(Dictionaries.getPriority(ticket.getPriority())));
        Assert.assertTrue(description.getText().contains(ticket.getDescription()));
        Assert.assertTrue(ticket.getDue_date().contains(swapDateFormat(dueDate.getText())));
    }

    private String swapDateFormat(String swapDate){
        swapDate = swapDate.substring(0, swapDate.indexOf(" ("));
        DateFormat fromFormat = new SimpleDateFormat("MMM. dd, yyyy, hh:mm", Locale.US);
        DateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
        Date date = null;
        try {
            date = fromFormat.parse(swapDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return toFormat.format(date);
    }
}
