package TestRunner;

import Pages.CreateEmployeePage;
import Pages.LoginPage;
import Pages.PIM_Page;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setupStart.Setup;
import utils.Utils;

public class EmployeeTestRunner extends Setup {

    LoginPage login;
    CreateEmployeePage EmployeePage;
    Utils util;
    @BeforeTest
    public void doLogin(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
        login = new LoginPage(driver);
        login.DoLogin();
    }
    @Test(priority = 1)
    public void EmployementPage(){
        EmployeePage = new CreateEmployeePage(driver);
        EmployeePage.employeePage.get(1).click();
        String urlActual = driver.getCurrentUrl();
        String urllExpected = "viewEmployeeList";
        Assert.assertTrue(urlActual.contains(urllExpected));

    }
    @Test(priority = 2)
    public void createEmployee() throws InterruptedException {
        Thread.sleep(2000);
        EmployeePage= new CreateEmployeePage(driver);
        util = new Utils();
        util.generateRandomData();
        String firstName = util.getFirstname();
        String lastName = util.getLastname();
        int randomId = utils.Utils.generateRandomNumber(1000,9999);
        String userName = util.getFirstname()+randomId;
        String password = "Sami@123456";
        String confirmPassword = password;
        EmployeePage.createEmployee(firstName,lastName,userName,password,confirmPassword);
    }

}
