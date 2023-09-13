import java.time.Month;
import java.util.*;

public class Lesson3Exercise3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Loan amount: £");
        float loan_cost = input.nextFloat();

        System.out.print("Interest Rate (APR %): ");
        float interest_rate = input.nextFloat();

        System.out.print("Number of years: ");
        int loan_years = input.nextInt();
        float month_rate = (interest_rate/100/12);
        double Month_payment = loan_cost*(month_rate/ (1-Math.pow(1+month_rate,-loan_years*12)));
        System.out.format("Monthly payement for this loan = £%.2f",Month_payment);

    }
}
