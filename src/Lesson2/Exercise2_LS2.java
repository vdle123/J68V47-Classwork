package Lesson2;

import java.util.*;

public class Exercise2_LS2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Number 1: ");
        int Num1 = input.nextInt();
        System.out.print("Enter Number 2: ");
        int Num2 = input.nextInt();


        System.out.println(Num1+" + "+Num2 + " = "+(Num1+Num2));
        System.out.println(Num1+" - "+Num2 + " = "+(Num1-Num2));
        System.out.println(Num1+" * "+Num2 + " = "+(Num1*Num2));
        System.out.println(Num1+" / "+Num2 + " = "+(Num1/Num2));
        System.out.println(Num1+" % "+Num2 + " = "+(Num1%Num2));
        System.out.println(Num1+" ^ "+Num2 + " = "+(Math.pow(Num1,Num2)));
    }
}
