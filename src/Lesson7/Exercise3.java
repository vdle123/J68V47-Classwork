package Lesson7;

import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        int score = 0;
        int lives = 3;
        int userAnswer = 0;
        int answer = 0;
        int count = 1;
        while(count <= 10 && lives >0){

        }

    }
    public static int askQuestion(int number1, int number2) {
        Scanner input = new Scanner(System.in);
        System.out.print("What is " + number1 + " + " + number2 + "?");
        int userAnswer = input.nextInt();
        return userAnswer;
    }
}
