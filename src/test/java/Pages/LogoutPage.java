package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

    @FindBy(className = "oxd-userdropdown-tab")
    public WebElement btnProfileIcon;

    @FindBy(partialLinkText = "Logout")
    public WebElement btnlogout;

    public LogoutPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
