import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Scanner;

public class InputReader {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu").withResolverStyle(ResolverStyle.STRICT);
    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readString(String message){
        System.out.print(message);
        return scanner.nextLine();
    }

    public int readInt(String message){

        while(true){
            try{
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Invalid number, try again!");
            }
        }
    }

    public double readDouble(String message){

        while(true){
            try{
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Invalid number, try again!");
            }
        }
    }

    public OptionalInt readOptionalInt(String message){
        while(true){
            try{
                System.out.print(message);
                String value = scanner.nextLine();

                return (value.isEmpty())
                        ? OptionalInt.empty()
                        : OptionalInt.of(Integer.parseInt(value));

            }catch(NumberFormatException e){
                System.out.println("Invalid number, try again!");
            }
        }
    }

    public OptionalDouble readOptionalDouble(String message){
        while(true){
            try{
                System.out.print(message);
                String value = scanner.nextLine();

                return (value.isEmpty())
                        ? OptionalDouble.empty()
                        : OptionalDouble.of(Double.parseDouble(value));

            }catch(NumberFormatException e){
                System.out.println("Invalid number, try again!");
            }
        }
    }

    public Category readCategory(String message) {
        while (true) {
            System.out.println(message);
            printCategories();

            Optional<Category> category = Category.fromNumber(readInt("Enter a number: "));

            if (category.isPresent()) {
                return category.get();
            }

            System.out.println("Invalid category! Please try again.");
        }
    }

    public Optional<Category> readOptionalCategory(String message) {
        while (true) {
            System.out.println(message);
            printCategories();

            OptionalInt number = readOptionalInt("Enter a number: ");
            if(number.isPresent()){
                Optional<Category> category = Category.fromNumber(number.getAsInt());

                if (category.isPresent()) {
                    return category;
                }
                System.out.println("Invalid category! Please try again.");
            }else{
                return Optional.empty();
            }
        }
    }

    public LocalDate readDate(String message){
        while(true){
            System.out.print(message);
            String dateString = scanner.nextLine();

            try{
                return LocalDate.parse(dateString, FORMATTER);
            }catch(DateTimeException e){
                System.out.println("Invalid date, try again!");
            }
        }
    }

    public Optional<LocalDate> readOptionalDate(String message){
        while(true){
            System.out.print(message);
            String dateString = scanner.nextLine();
            if(dateString.isEmpty()) return Optional.empty();

            try{
                return Optional.of(LocalDate.parse(dateString, FORMATTER));
            }catch(DateTimeException e){
                System.out.println("Invalid date, try again!");
            }
        }
    }

    private static void printCategories() {
        for(int i = 0; i < Category.values().length; i++){
            System.out.printf("%d: %s %n", i+1, Category.values()[i]);
        }
    }
}
