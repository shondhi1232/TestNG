package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDashboardPage {

    public UserDashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
