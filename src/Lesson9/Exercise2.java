package Lesson9;

import java.util.*;
import java.io.File;

public class Exercise2 {

    public static void exception1() {
        // exception 1
        try {
            int exception1 = 10 / 0;
            System.out.println(exception1);
        } catch (Exception e) {
            System.out.println(e.toString());
            // This error is caused because dividing by 0 is impossible
        }
    }

    public static void exception2() {
        // exception 2
        try {
            int exception2 = Integer.MAX_VALUE + 1;
        } catch (Exception e) {
            System.out.println(e.toString());
            // It is trying to add numbers above the limit
        }

    }

    public static void exception3() {
        // exception 3
        try {
            int[] myArray3 = new int[5];
            int exception3 = myArray3[5];
        } catch (Exception e) {
            System.out.println(e.toString());
            // This is trying to add a number above the limit of the array
        }

    }

    public static void exception4() {
        // exception 4
        try {
            int[] myArray4 = null;
            int exception4 = myArray4[0];
        } catch (Exception e) {
            System.out.println(e.toString());
            // This array is trying to add nothing
        }

    }

    public static void exception5() {
        // exception 5
        try {
            File exception5 = new File("exception5.txt");
            Scanner scanner = new Scanner(exception5);
        } catch (Exception e) {
            System.out.println(e.toString());
            // The file is in a different location or does not exist
        }

    }

    public static void exception6() {
        // exception 6
        try {
            String thisString = "Hello";
            int exception6 = Integer.parseInt(thisString);
        } catch (Exception e) {
            System.out.println(e.toString());
            // This is trying to use Integer functions for a string variable
        }

    }

    public static void exception7() {
        // exception 7
        try {
            String exception7 = null;
            System.out.println(exception7.toUpperCase());
        } catch (Exception e) {
            System.out.println(e.toString());
            // It is trying to change the exception7 to upper case but upper case has nothing stored
        }

    }

    public static void main(String[] args) {
        exception1();
        exception2();
        exception3();
        exception4();
        exception5();
        exception6();
        exception7();

    }
}