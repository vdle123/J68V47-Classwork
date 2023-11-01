package Lesson6;

import java.util.Random;
import java.util.Scanner;

class Exercise3_LS6 {
    public static void main(String[] args) {
        Scanner input1 = new Scanner(System.in);
        int score = 0, lives = 3;
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            if (lives == 0){
                System.out.println("You ran out of lives!");
                break;
            }
            int num1 = random.nextInt(1, 20), num2 = random.nextInt(1, 50);
            System.out.println(num1 + " + " + num2 + " = :");
            int answer = input1.nextInt();
            if (answer == (num1 + num2)) {
                score++;
            }else{
                lives--;
            }
        }
        if(lives==3){
            System.out.println("Well done");
        }
        System.out.println("Your score: " + score + "/10");
    }
}
