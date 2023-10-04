import java.util.Scanner;

public class Exercise1_LS5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = input.nextInt();
        for (int i = 1; i<=12;i++){
            System.out.println(i+" x "+number+" = "+i*number);
        }
    }
}
