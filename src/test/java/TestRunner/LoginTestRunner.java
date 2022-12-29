package TestRunner;

import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.PIM_Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import setupStart.Setup;
import utils.Utils;

import java.util.List;

public class LoginTestRunner extends Setup {
    LoginPage login;
    DashboardPage dashboardPage;
    PIM_Page PimPage;

    @Test(priority = 1,description = "User can not login with wrong credentials")
    public void doLoginWithWrongData(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
        login = new LoginPage(driver);
        String username = "admin";
        String password = "Admin123";
        login.DoLogin(username,password);
        String validationErrorActual =driver.findElement(By.className("oxd-alert-content-text")).getText();
        String validationErrorExtual ="Invalid credentials";
        Assert.assertTrue(validationErrorActual.contains(validationErrorExtual));

    }

    @Test(priority = 2, description = "Admin login successfully")
    public void doLogin(){
        login = new LoginPage(driver);
        login.DoLogin("Admin","admin123");
        String urlActual = driver.getCurrentUrl();
        String urllExpected = "dashboard";
        Assert.assertTrue(urlActual.contains(urllExpected));
    }

    @Test(priority = 3)
    public void checkProfile_ImgExist(){
        //WebElement ImgProfile =  driver.findElement(By.className("oxd-userdropdown-img"));

        dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.imgProfile.isDisplayed());
    }
    @Test(priority = 4,description = "swtich into employee page from dashboard page")
    public void EmployementPage(){
        PimPage = new PIM_Page(driver);
        PimPage.employeePage.get(1).click();
        String urlActual = driver.getCurrentUrl();
        String urllExpected = "viewEmployeeList";
        Assert.assertTrue(urlActual.contains(urllExpected));

    }
    @Test(priority = 5, description = "select employee status and search to show the list")
    public void selectEmployementStatus() throws InterruptedException {
        WebElement selectBtn = PimPage.dropdowns.get(0);
        Thread.sleep(3000);
        selectBtn.click();
        selectBtn.sendKeys(Keys.ARROW_DOWN);
        selectBtn.sendKeys(Keys.ARROW_DOWN);
        //selectBtn.sendKeys(Keys.ARROW_DOWN);
        selectBtn.sendKeys(Keys.ENTER);
        PimPage.btnSubmit.get(0).click();

       Thread.sleep(5000);
        String actualData = PimPage.txtData.get(14).getText();
        String expectedData = "Records Found";
        Assert.assertTrue(actualData.contains(expectedData));

    }
    @Test(priority = 6,description = "Showing employee list which have selected")
    public void listEmployee() throws InterruptedException {
        Utils.doScroll(driver);

        Thread.sleep(3000);
        List<WebElement> allRow = PimPage.table.findElements(By.cssSelector("[role=row]"));

        for (WebElement row:allRow){
            List<WebElement> cells = row.findElements(By.cssSelector("[role = cell]"));
            String dataActual = cells.get(5).getText();
            String dataExpected = "Full-Time Contract";
            Assert.assertTrue(dataActual.contains(dataExpected));

        }

    }
    @Test(priority = 7,description = "Again click in employee status and search for freeelance employee for geting no records found",enabled = false)
    //enable false means this function will not run
    //orangehrm website some-times makes mess like now it can not identify the search button
    // that's why I make it enable false.
    public void noEmployeeData() throws InterruptedException {

        WebElement selectBtn = PimPage.dropdowns.get(0);
        Thread.sleep(3000);
        selectBtn.click();
        selectBtn.sendKeys(Keys.ARROW_DOWN);
        selectBtn.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        PimPage.btnSubmit.get(0).click();
        //List<WebElement> searchBtn = driver.findElements(By.cssSelector("[type=submit]"));
        //searchBtn.get(3).click();

        Thread.sleep(2000);
        String actualData = PimPage.txtData.get(14).getText();
        String expectedData = "No Records Found";
        Assert.assertTrue(actualData.contains(expectedData));
    }


    /*
    @Test (priority = 6)
    public void addEmployeePage() throws InterruptedException {

        PimPage.btnAddEmployee.get(2).click();
        Thread.sleep(3000);
        String actualData = PimPage.addEmployeeStatus.get(12).getText();
        String expectedData = "Add Employee";
        Assert.assertTrue(actualData.contains(expectedData));
    }

    @Test(priority = 7)
    public void setEmployeeDetails(){
        PimPage.txtFirstName.sendKeys("Sami");
        PimPage.txtLastName.sendKeys("Sam");
    }
    @Test(priority = 8)
    public void createLoginDetails(){
        PimPage.btnSwitchLoginDetails.click();
        PimPage.txtUserName.get(5).sendKeys("sami1234");

        PimPage.btnSubmit.get(0).click();
    }

     */


}
