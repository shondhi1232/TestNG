package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDashboardPage {

    @FindBy(className ="oxd-userdropdown-name")
    public WebElement ProfileName;

    public UserDashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
