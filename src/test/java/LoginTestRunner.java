import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.PIM_Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTestRunner extends Setup{
    LoginPage login;
    DashboardPage dashboardPage;
    PIM_Page PimPage;
    @Test(priority = 1)
    public void doLogin(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
        login = new LoginPage(driver);
        login.DoLogin();
        String urlActual = driver.getCurrentUrl();
        String urllExpected = "dashboard";
        Assert.assertTrue(urlActual.contains(urllExpected));
    }

    @Test(priority = 2)
    public void checkProfile_ImgExist(){
        //WebElement ImgProfile =  driver.findElement(By.className("oxd-userdropdown-img"));

        dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.imgProfile.isDisplayed());
    }
    @Test(priority = 3)
    public void EmployementPage(){
        PimPage = new PIM_Page(driver);
        PimPage.employeePage.get(1).click();
        String urlActual = driver.getCurrentUrl();
        String urllExpected = "viewEmployeeList";
        Assert.assertTrue(urlActual.contains(urllExpected));

    }
    @Test(priority = 4)
    public void selectEmployementStatus() throws InterruptedException {
        WebElement selectBtn = PimPage.dropdowns.get(0);
        Thread.sleep(3000);
        selectBtn.click();
        selectBtn.sendKeys(Keys.ARROW_DOWN);
        selectBtn.sendKeys(Keys.ARROW_DOWN);
        //selectBtn.sendKeys(Keys.ARROW_DOWN);
        selectBtn.sendKeys(Keys.ENTER);
        PimPage.btnSubmit.click();

       Thread.sleep(5000);
        String actualData = PimPage.txtData.get(14).getText();
        String expectedData = "(5) Records Found";
        Assert.assertTrue(actualData.contains(expectedData));

    }
    @Test(priority = 5)
    public void listEmployee() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //javaScript scroll down code, and it is executing by javascriptExecutor and it is done through driver.
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        Thread.sleep(3000);
        List<WebElement> allRow = PimPage.table.findElements(By.cssSelector("[role=row]"));

        for (WebElement row:allRow){
            List<WebElement> cells = row.findElements(By.cssSelector("[role = cell]"));
            String dataActual = cells.get(5).getText();
            String dataExpected = "Full-Time Contract";
            Assert.assertTrue(dataActual.contains(dataExpected));

        }

    }
    @Test (priority = 6)
    public void addEmployee() throws InterruptedException {

        PimPage.btnAdd.get(2).click();
        Thread.sleep(3000);
        String actualData = PimPage.addEmployeeStatus.get(12).getText();
        String expectedData = "Add Employee";
        Assert.assertTrue(actualData.contains(expectedData));
    }


}
