package Lesson8;

public class Exercise2 {
    public static void main(String[] args) {
        int[] scores = {15,24,12,51,61,75,32,63,68,12};
        float total = 0;
        for (int value : scores){
            System.out.println(value);
            total+=value;
        }
        System.out.println("The average score is: "+total/scores.length);
    }


}
