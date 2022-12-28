package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserMy_info_Page {

    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> myInfoPage;

    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdown;

    public UserMy_info_Page(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
