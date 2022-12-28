package TestRunner;

import Pages.LoginPage;
import Pages.UserDashboardPage;
import Pages.UserMy_info_Page;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import setupStart.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class UserLogin_and_UpdateTestRunner extends Setup {

    UserDashboardPage userDashboard;
    UserMy_info_Page myInfo_Page;

    @Test(priority = 1)
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
    @Test(priority = 2)
    public void userMy_info_page(){
        myInfo_Page = new UserMy_info_Page(driver);
        myInfo_Page.myInfoPage.get(2).click();

        String urlActual = driver.getCurrentUrl();
        String urlExpected = "viewPersonalDetails";
        Assert.assertTrue(urlActual.contains(urlExpected));
    }
    public void update_userInfo(){
        
    }

}
