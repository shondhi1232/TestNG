package TestRunner;

import Pages.CreateEmployeePage;
import Pages.LoginPage;
import Pages.PIM_Page;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setupStart.Setup;

public class EmployeeTestRunner extends Setup {

    LoginPage login;
    //
    // PIM_Page PimPage;
    CreateEmployeePage EmployeePage;

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
        String firstName = "sami";
        String lastName = "Sam";
        String userName = "sami1234";
        String password = "Sami@123456";
        String confirmPassword = "Sami@123456";
        EmployeePage.createEmployee(firstName,lastName,userName,password,confirmPassword);
    }

}
