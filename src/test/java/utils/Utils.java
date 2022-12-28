package utils;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Utils {
    public static int generateRandomNumber(int min, int max){
       int num =(int) Math.floor(Math.random()*(max-min)+min);
       return num;
    }

    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void generateRandomData(){
        Faker faker = new Faker();

        setFirstname(faker.name().firstName());
        setLastname(faker.name().lastName());
    }
    //write JSON array codes
    public static void saveJsonListData(String username, String password) throws IOException, ParseException  {

        String fileName = "./src/test/resources/EmployeeList.json";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;

        JSONObject userObj = new JSONObject();
        userObj.put("userName",username);
        userObj.put("Password",password);
        jsonArray.add(userObj);


        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
        System.out.println("Saved Data");

    }

    public static void doScroll(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //javaScript scroll down code, and it is executing by javascriptExecutor and it is done through driver.
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public static List readJSON_array(String filename) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        JSONArray jsonArray = (JSONArray) obj;
        return jsonArray;
    }
    public static void waitForElement(WebDriver driver, WebElement headerTitle, int Time_Unit_Seconds){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Time_Unit_Seconds));
        wait.until(ExpectedConditions.visibilityOf(headerTitle));

    }
/*
    public static void main(String[] args) throws IOException, ParseException {

        Utils util = new Utils();

        int rand = generateRandomNumber(10,50);
        System.out.println(rand);

        util.generateRandomData();
        System.out.println(util.getFirstname());
        System.out.println(util.getLastname());

       util.saveJsonListData("sami1234","Sami@123456");

        List data = Utils.readJSON_array("./src/test/resources/EmployeeList.json");
        JSONObject obj = (JSONObject) data.get(1);
        System.out.printf((String) obj.get("userName"));
    }

 */

}
