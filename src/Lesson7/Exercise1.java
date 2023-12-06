package Lesson7;

import java.util.Scanner;

public class Exercise1 {

    public static void main(String[] args) {
        int option = 0;
        showMenu();
        while (option != 4) {
            option = getOption();
            switch (option){
                case 1 -> option1();
                case 2 -> option2();
                case 3 -> option3();
                case 4 -> option4();
                default -> System.out.println("This isn't a valid option. Try a number between 1 and 4.");
            }

        }
    }

    public static void showMenu() {
        System.out.println("Menu");
        System.out.println("1. Say hello");
        System.out.println("2. Tell me the time");
        System.out.println("3. Tell me a joke");
        System.out.println("4. Quit");
    }

    public static int getOption() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public static void option1() {
        System.out.println("HELLO!");
    }

    public static void option2() {
        System.out.println("The time is now.");
    }

    public static void option3() {
        System.out.println("Knock knock. Who's there? ....... long pause ........ Java");
    }

    public static void option4() {
        System.out.println("Bye!");
    }
}
