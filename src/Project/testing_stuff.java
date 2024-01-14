package Project;
import java.time.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;
public class testing_stuff {
    public static void create_file(){
        try{
            File myObj = new File("goals.txt");
            if(myObj.createNewFile()){
                System.out.println("File has been created");
            }else{
                System.out.println("File exists");
            }
        }catch(IOException e){
            System.out.println("Error");
        }}
    public static void change_file() {
        try {
            FileWriter myWriter = new FileWriter("goals.txt");
            myWriter.write(String.valueOf(LocalDate.now())); //Writes current date to the file
            myWriter.close();
            System.out.println("Wrote");
        } catch (IOException e) {
            System.out.println("error");
        }

    }
    public static void readfile(){
        try{
            File myObj = new File("goals.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){

                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();

        } catch (FileNotFoundException e){
            System.out.println("Error has occured");
        }}
    public static void write_multiple_things(){
        String variable1 = "Hello";
        int variable2 = 42;
        double variable3 = 3.14;

        // Specify the file path
        String filePath = "output.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Concatenate the variables and write to the file
            writer.write(variable1 + "," + variable2 + "," + variable3);
            writer.newLine(); // Add a newline for the next set of variables
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public static void main(String[] args) {
        //readfile();
        write_multiple_things();
    }
}
