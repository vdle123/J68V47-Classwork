import java.util.Scanner;

public class Exercise3_LS4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your total purchase amount: £");
        float purchase_amount = input.nextFloat();
        if(50>purchase_amount){
            purchase_amount+=10;
            System.out.println("Shipping cost is = £10.00");
        }else {
            System.out.println("Shipping cost is = £0.00");
        }
        System.out.println("Your final total is = £"+purchase_amount);


    }
}
