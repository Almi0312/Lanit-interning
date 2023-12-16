package pages;

import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/** Страница создания тикета */
public class CreateTicketPage extends HelpdeskBasePage {

    @FindBy(xpath = "//select[@name='queue']")
    private WebElement selectQueue;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement inputProblem;

    @FindBy(xpath = "//textarea[@name='body']")
    private WebElement inputDescriptionProblem;

    @FindBy(xpath = "//select[@name='priority']")
    private WebElement selectPriority;

    @FindBy(xpath = "//button[@class = 'btn btn-primary btn-lg btn-block']")
    private WebElement submitTicketButton;

    @FindBy(xpath = "//input[@name='due_date']")
    private WebElement inputDueOnProblem;

    @FindBy(xpath = "//input[@name='submitter_email']")
    private WebElement inputSubmitterEMailAddress;

    public CreateTicketPage() {
        PageFactory.initElements(driver,this);
    }

    @Step("Создать тикет")
    public CreateTicketPage createTicket(Ticket ticket) {
        setSelectQueue(String.valueOf(ticket.getQueue()));              //поле queue
        setInputProblem(ticket.getTitle());                             //поле title
        setInputDescriptionProblem(ticket.getDescription());            //поле description
        setSelectPriority(String.valueOf(ticket.getPriority()));        //поле priority
        setInputDueOnProblem(ticket.getDue_date());                     //поле due_date
        setInputSubmitterEMailAddress(ticket.getSubmitter_email());     //поле submitter_email_address
        clickOnSubmitButton();                                          //кнопка submit_ticket
        return this;
    }

    @Step("Выбрать очередь проблемы: {text}")
    public void setSelectQueue(String text) {
        selectQueue.click();
        driver.findElement(By.xpath("//select[@id='id_queue']//option[@value='"+text+"']"))
                .click();
    }
    @Step("Ввести имя проблемы: {text}")
    public void setInputProblem(String text) {
        inputProblem.sendKeys(text);
    }
    @Step("Ввести описание проблемы на кириллице: {text}")
    public void setInputDescriptionProblem(String text) {
        inputDescriptionProblem.sendKeys(text);
    }
    @Step("Ввести приоритет проблемы: {text}")
    public void setSelectPriority(String text) {
        selectPriority.click();
        driver.findElement(By.xpath("//select[@name='priority']//option[@value='"+text+"']"))
                .click();
    }
    @Step("Ввести дату к выполнению проблемы: {text}")
    public void setInputDueOnProblem(String text) {
        inputDueOnProblem.sendKeys(text);
    }
    @Step("Ввести корректный e-mail: {text}")
    public void setInputSubmitterEMailAddress(String text) {
        inputSubmitterEMailAddress.sendKeys(text);
    }

    @Step("Нажать на кнопку создания тикета")
    public void clickOnSubmitButton() {
        submitTicketButton.click();
    }
}
