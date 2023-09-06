import java.util.*;
public class Exercise1_LS2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What's your name? ");
        String Name = input.nextLine();

        System.out.print("What's your hobby? ");
        String Hobby = input.nextLine();

        System.out.println(Name+" loves "+Hobby+" in their free time.\nThey find it relaxing and satisfying to see their "+Hobby+" come to life.\n"+Hobby+" allows "+Name+" to express their creative side.");
    }
}
