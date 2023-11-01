package Lesson4;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class Extra1_LS4 {
    public static void main(String[] args) {
        int randomNum = ThreadLocalRandom.current().nextInt(1,  10 + 1);
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Enter a number(1-10): ");
            int number = input.nextInt();
            if(number==randomNum) {
                System.out.println("Correct number!");
                break;
            } else if (number>randomNum) {
                System.out.println("The number is lower");
            }else{
                System.out.println("The number is higher");
            }
        }

    }
}
