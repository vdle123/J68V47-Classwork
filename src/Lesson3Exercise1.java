import java.util.Scanner;

public class Lesson3Exercise1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your first name: ");
        String name = input.nextLine();
        System.out.println("Enter your surname: ");
        String surname = input.nextLine();
        System.out.println("Enter your year of birth YYYY: ");
        String dob = input.nextLine();
        String username = name.substring(0, 1).toUpperCase() + surname.toLowerCase();
        System.out.println("Username: " + username);
        String password = surname.toLowerCase().substring(0, 1) + name.toUpperCase().substring(0, 3) + dob;
        System.out.println("Password: " + password);
    }
}
