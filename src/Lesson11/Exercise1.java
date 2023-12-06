package Lesson11;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Exercise1 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"))){
            for (int i = 0; i <= 11; i++){
                out.println((i+1)+ " x "+ num+" = " +(i+1) * num);
            }
        }
    }
}
