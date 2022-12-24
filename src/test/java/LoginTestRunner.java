import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTestRunner extends Setup{
    LoginPage login;
    DashboardPage dashboardPage;
    PIM_Page PimPage;
    @Test
    public void _1doLogin(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
        login = new LoginPage(driver);
        login.DoLogin();
        String urlActual = driver.getCurrentUrl();
        String urllExpected = "dashboard";
        Assert.assertTrue(urlActual.contains(urllExpected));
    }

    @Test
    public void _2checkProfile_ImgExist(){
        //WebElement ImgProfile =  driver.findElement(By.className("oxd-userdropdown-img"));

        dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.imgProfile.isDisplayed());
    }
    @Test
    public void _3EmployementPage(){
        PimPage = new PIM_Page(driver);
        PimPage.employeePage.get(1).click();
        String urlActual = driver.getCurrentUrl();
        String urllExpected = "viewEmployeeList";
        Assert.assertTrue(urlActual.contains(urllExpected));

    }
    @Test
    public void _4selectEmployementStatus() throws InterruptedException {
        PimPage.dropdowns.get(0).click();
        PimPage.dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
        PimPage.dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
        PimPage.dropdowns.get(0).sendKeys(Keys.ENTER);
        PimPage.btnSubmit.click();

        Thread.sleep(3000);
        List<WebElement> textData = driver.findElements(By.tagName("span"));
        String actualData = textData.get(14).getText();
        String expectedData = "Records Found";
        Assert.assertTrue(actualData.contains(expectedData));
    }
}
