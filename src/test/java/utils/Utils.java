package utils;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public void saveJsonListData(String username, String password) throws IOException, ParseException  {

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
/*
    public static void main(String[] args) throws IOException, ParseException {

        Utils util = new Utils();
        int rand = generateRandomNumber(10,50);
        System.out.println(rand);

        util.generateRandomData();
        System.out.println(util.getFirstname());
        System.out.println(util.getLastname());

       util.saveJsonListData("sami1234","Sami@123456");
       // util.saveJsonListData();

    }
 */
}
