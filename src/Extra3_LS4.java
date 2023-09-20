import java.util.Scanner;

public class Extra3_LS4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number between 1-7: ");
        int score = input.nextInt();
        if(score>7||score<1){
            System.out.println("Number is out of range.");
        }
        switch(score){
            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wednesday");
            case 4 -> System.out.println("Thursday");
            case 5 -> System.out.println("Friday");
            case 6 -> System.out.println("Saturday");
            case 7 -> System.out.println("Sunday");

        }
    }
}
