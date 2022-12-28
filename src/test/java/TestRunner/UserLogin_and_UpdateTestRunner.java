package TestRunner;

import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.UserDashboardPage;
import Pages.UserMy_info_Page;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setupStart.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class UserLogin_and_UpdateTestRunner extends Setup {

    UserDashboardPage userDashboard;
    UserMy_info_Page myInfo_Page;

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/");

        List data = Utils.readJSON_array("./src/test/resources/EmployeeList.json");
        LoginPage login = new LoginPage(driver);

        ///json array last data will return through the below code///
        JSONObject userobj = (JSONObject) data.get(data.size()-1);
        String username = (String) userobj.get("userName");
        String password= (String) userobj.get("Password");
        login.DoLogin(username,password);

        userDashboard = new UserDashboardPage(driver);
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "dashboard";
        Assert.assertTrue(urlActual.contains(urlExpected));

    }
    @Test(priority = 1)
    public void userMy_info_page(){
        myInfo_Page = new UserMy_info_Page(driver);
        myInfo_Page.myInfoPage.get(2).click();

        String urlActual = driver.getCurrentUrl();
        String urlExpected = "viewPersonalDetails";
        Assert.assertTrue(urlActual.contains(urlExpected));
    }
    @Test(priority = 2)
    public void update_userInfo(){

        List <WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Utils.waitForElement(driver,headerTitle.get(0),20);
        Assert.assertTrue(headerTitle.get(0).isDisplayed());

        if (headerTitle.get(0).isDisplayed()){
            WebElement nationalitySelect = myInfo_Page.dropdown.get(0);
            nationalitySelect.click();
            nationalitySelect.sendKeys("b");
            nationalitySelect.sendKeys(Keys.ARROW_DOWN);
            nationalitySelect.sendKeys(Keys.ARROW_DOWN);
            nationalitySelect.sendKeys(Keys.ENTER);

            Utils.doScroll(driver);
            myInfo_Page.submit.get(0).click();
        }
    }
    @AfterTest
    public void logout(){

        LogoutPage logout =new LogoutPage(driver);
        logout.btnProfileIcon.click();
        logout.btnlogout.click();
    }

}
