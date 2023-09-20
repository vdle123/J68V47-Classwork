import java.util.Scanner;

public class Exercise1_LS4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your First name: ");
        String forename = input.nextLine();

        System.out.print("Enter your surname: ");
        String surname = input.nextLine();

        System.out.print("Enter the total value of your order: £");
        float order_value = input.nextFloat();

        System.out.print("Enter the amount you wish to pay as deposit: £: ");
        float deposit_amount = input.nextFloat();

        System.out.println("== RECEIPT ==");
        System.out.println("Customer: "+forename.charAt(0)+" "+surname);
        System.out.println("Order Total: £"+order_value);
        System.out.println("Deposit paid: £"+deposit_amount);
        System.out.println("Remainder: £"+(order_value-deposit_amount));
        System.out.println("You get a free toaster!\nHave a nice day");
    }
}
