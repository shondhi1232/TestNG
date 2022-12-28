package TestRunner;

import Pages.CreateEmployeePage;
import Pages.LoginPage;
import Pages.PIM_Page;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setupStart.Setup;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class EmployeeTestRunner extends Setup {

    LoginPage login;
    CreateEmployeePage EmployeePage;
    Utils util;
    @BeforeTest
    public void doLogin(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
        login = new LoginPage(driver);
        String username = "Admin";
        String password = "admin123";
        login.DoLogin(username,password);
    }
    @Test(priority = 1)
    public void EmployementPage(){
        EmployeePage = new CreateEmployeePage(driver);
        EmployeePage.employeePage.get(1).click();
        String urlActual = driver.getCurrentUrl();
        String urllExpected = "viewEmployeeList";
        Assert.assertTrue(urlActual.contains(urllExpected));

    }
    @Test(priority = 2,description = "check if user already exists")
    //negative test case
    public void checkIfUserExist() throws IOException, ParseException {
        EmployeePage= new CreateEmployeePage(driver);
        EmployeePage.btnAddEmployee.get(2).click();
        EmployeePage.btnSwitchLoginDetails.click();
        List data = Utils.readJSON_array("./src/test/resources/EmployeeList.json");
        JSONObject userobj = (JSONObject) data.get(data.size()-1);
        String existUsername = (String) userobj.get("userName");
        String validationMassageActual = EmployeePage.checkIfUserExist(existUsername);
        String validationMassageExpected = "Username already exists";
        Assert.assertTrue(validationMassageActual.contains(validationMassageExpected));
    }

    @Test(priority = 3,description = "create new employee as a admin")
    public void createEmployee() throws InterruptedException, IOException, ParseException {
        Thread.sleep(2000);
        EmployeePage= new CreateEmployeePage(driver);
        util = new Utils();

        util.generateRandomData();
        String firstName = util.getFirstname();
        String lastName = util.getLastname();

        int randomId = utils.Utils.generateRandomNumber(1000,9999);
        String userName = util.getFirstname()+randomId;
        String password = "Sam@123456";
        String confirmPassword = password;
        EmployeePage.txtUserName.get(5).clear();
        EmployeePage.createEmployee(firstName,lastName,userName,password,confirmPassword);

        //we will wait until the header title visible and then will save data in json array
        List <WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        util.waitForElement(driver,headerTitle.get(0),20);
        Assert.assertTrue(headerTitle.get(0).isDisplayed());

        if (headerTitle.get(0).isDisplayed()){
            util.saveJsonListData(userName,password);
        }
    }

}
