import java.util.Scanner;

public class Lesson3Exercise2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Q1. What is the capital of Spain? ");
        String Q1 = input.nextLine();
        System.out.println(Q1.toUpperCase().contains("MADRID"));

        System.out.println("Q2. What is the capital of UK? ");
        String Q2 = input.nextLine();
        System.out.println(Q2.toUpperCase().contains("LONDON"));

        System.out.println("Q3. What is the capital of Italy? ");
        String Q3 = input.nextLine();
        System.out.println(Q3.toUpperCase().contains("ROME"));
    }
}
