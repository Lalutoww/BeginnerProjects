import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradesList {
    static void main(String[] args) {
        List<Double> grades = getGrades();


        if(!grades.isEmpty()){
            System.out.println("Size: " + grades.size());
            System.out.printf("Average grade: %.2f%n", getAverageGrade(grades));
            System.out.println("Worst grade: " + getWorstGrade(grades));
            System.out.println("Best grade: " + getBestGrade(grades));
        }else{
            System.out.println("No grades are logged!");
        }



    }

    private static double getWorstGrade(List<Double> grades) {
        double worstGrade = Double.POSITIVE_INFINITY;

        for(double grade : grades){
            if(grade < worstGrade){
                worstGrade = grade;
            }
        }

        return worstGrade;
    }

    private static double getBestGrade(List<Double> grades) {
        double bestGrade = Double.NEGATIVE_INFINITY;

        for(double grade : grades){
            if(grade > bestGrade){
                bestGrade = grade;
            }
        }

        return bestGrade;

    }

    private static double getAverageGrade(List<Double> grades) {
        double sum = 0;

        for(double grade : grades){
            sum+=grade;
        }

        return sum / grades.size();
    }

    private static List<Double> getGrades(){
        Scanner sc = new Scanner(System.in);

        List<Double> grades = new ArrayList<>();


        while (true) {
            System.out.print("Please enter a grade (-1 to finish): ");
            double input = sc.nextDouble();

            if(input == -1){
                break;
            }

            if(input < 2 || input > 6){
                System.out.println("Invalid grade, please try again!");
                continue;
            }

            grades.add(input);

        }

        return grades;

    }
}
