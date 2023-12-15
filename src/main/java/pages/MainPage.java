package pages;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

/** Главная страница */
public class MainPage extends HelpdeskBasePage {

    public MainPage() {PageFactory.initElements(driver, this);
    }

}
