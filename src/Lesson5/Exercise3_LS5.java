package Lesson5;

import java.util.Random;
import java.util.Scanner;

class Exercise3_LS5 {
    public static void main(String[] args) {
        Scanner input1 = new Scanner(System.in);
        int score = 0;
        Random random = new Random();
        for (int i=1;i<=10;i++){
            int num1 = random.nextInt(1, 20);
            int num2 = random.nextInt(1, 50);
            System.out.println(num1 +" + " +num2 + " = :");
            int answer = input1.nextInt();
            if (answer==(num1+num2)){
                score++;
            }


        }
        System.out.println("Your score: "+score+"/10");
    }
}
