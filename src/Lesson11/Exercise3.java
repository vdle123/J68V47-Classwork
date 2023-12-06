package Lesson11;

import java.io.*;
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) throws IOException {
        int sum = 0;
        try(Scanner in = new Scanner(new FileReader("output1.txt"))){
            while(in.hasNextInt()){
                sum += in.nextInt();
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        System.out.println(sum);
    }

}
