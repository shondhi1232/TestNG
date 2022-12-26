package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIM_Page {

    @FindBy(className = "oxd-main-menu-item-wrapper")
    public List<WebElement> employeePage;

    @FindBy(className ="oxd-select-text-input")
    public List<WebElement> dropdowns;

    @FindBy(css ="[type=submit]")
    public WebElement btnSubmit;

    @FindBy(tagName = "span")
    public List<WebElement> txtData;

    @FindBy(className = "oxd-table-body")
    public WebElement table;

    @FindBy(className = "oxd-button")
    public List<WebElement> btnAdd;

    @FindBy(className = "oxd-text")
    public List<WebElement> addEmployeeStatus;
    public PIM_Page(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
