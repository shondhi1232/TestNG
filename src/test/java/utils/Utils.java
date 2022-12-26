package utils;

public class Utils {
    public static int generateRandomNumber(int min, int max){
       int num =(int) Math.floor(Math.random()*(max-min)+min);
       return num;
    }

  /*  public static void main(String[] args) {

        int rand = generateRandomNumber(10,50);
        System.out.println(rand);
    }
    
   */
}
