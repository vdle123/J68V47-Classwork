package Lesson2;

import java.util.*;

public class Exercise3_LS2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Item 1 cost: ");
        float Item1_cost = input.nextFloat();

        System.out.print("Enter Item 2 cost: ");
        float Item2_cost = input.nextFloat();

        System.out.print("Enter Item 3 cost: ");
        float Item3_cost = input.nextFloat();

        System.out.format("Item 1:... £%.2f\n", Item1_cost);
        System.out.format("Item 2:... £%.2f\n", Item2_cost);
        System.out.format("Item 3:... £%.2f\n", Item3_cost);
        System.out.format("SUBTOTAL.."+" £%.2f",(Item1_cost+Item2_cost+Item3_cost));
    }
}
