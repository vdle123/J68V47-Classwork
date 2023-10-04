import java.util.Scanner;

public class Exercise2_LS6 {
    public static void main(String[] args) {
        while (true){
            Scanner input = new Scanner(System.in);
            System.out.print("1. Say 'hello'\n2. Tell me the time\n3. Tell me a joke.\n4. Quit\n");
            int option = input.nextInt();
            if (option ==4){
                break;
            }
        }
    }
}
