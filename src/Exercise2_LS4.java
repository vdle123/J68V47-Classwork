import java.util.*;
public class Exercise2_LS4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Q1. What is the capital of Spain? ");
        String Q1 = input.nextLine();
        if(!Q1.toLowerCase().contains("madrid"))
            System.out.println("Sorry, the correct answer is Madrid.");

        System.out.println("Q2. What is the capital of UK? ");
        String Q2 = input.nextLine();
        if(!Q2.toLowerCase().contains("london"))
            System.out.println("Sorry, the correct answer is London.");

        System.out.println("Q3. What is the capital of Italy? ");
        String Q3 = input.nextLine();
        if(!Q3.toLowerCase().contains("rome"))
            System.out.println("Sorry, the correct answer is Rome.");
    }
}
