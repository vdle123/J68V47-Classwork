package Lesson11;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class Exercise2 {
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("output.txt"));

        while (true){
            String line = in.readLine();
            if (line == null){
                break;
            }
            System.out.println(line);
        }

    }
}
