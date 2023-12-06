package Lesson7;

import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        int a = input.nextInt();

        System.out.print("Enter the second number: ");
        int b = input.nextInt();
        System.out.println("The sum of "+a+" and "+b+" is "+calculateSum(a,b));
    }

    public static int calculateSum(int a, int b) {
        return a + b;
    }

}
