package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateEmployeePage {

    @FindBy(className = "oxd-main-menu-item-wrapper")
    public List<WebElement> employeePage;

    @FindBy(className = "oxd-button")
    public List<WebElement> btnAddEmployee;

    @FindBy(className = "oxd-text")
    public List<WebElement> addEmployeeStatus;

    @FindBy(name = "firstName")
    public WebElement txtFirstName;

    @FindBy(name = "lastName")
    public WebElement txtLastName;

    @FindBy(className = "oxd-switch-input")
    public WebElement btnSwitchLoginDetails;

    @FindBy(className = "oxd-input")
    public List<WebElement> txtUserName;

    @FindBy(css = "[type =password]")
    public List<WebElement> password;

    @FindBy(css = "[type=password]")
    public List<WebElement> confirmPassword;

    @FindBy(css ="[type=submit]")
    public List<WebElement> btnSubmit;

    @FindBy(className = "oxd-input-field-error-message")
    public WebElement validationError;

    @FindBy(className = "oxd-input oxd-input--active")
    public List<WebElement> emplyeeId;

    public CreateEmployeePage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }
    public String checkIfUserExist(String username){
       // btnAddEmployee.get(2).click();
        txtUserName.get(5).sendKeys(username);

        return validationError.getText();
    }

    public void createEmployee(String firstName,String lastName,String userName,String passwrd,String confirmPasswrd){

        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        //btnSwitchLoginDetails.click();
        txtUserName.get(5).sendKeys(userName);
        password.get(0).sendKeys(passwrd);
        confirmPassword.get(1).sendKeys(confirmPasswrd);

        btnSubmit.get(0).click();
    }



}
