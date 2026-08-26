import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseApp {
    private final Scanner scanner;
    private final ExpenseTracker expenseTracker;

    public ExpenseApp(){
        this.scanner = new Scanner(System.in);
        this.expenseTracker = new ExpenseTracker();
    }

    public void start(){
        printMenu();
        while(true){
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            switch (command.toLowerCase()){
                case "help" -> printMenu();
                case "add" -> addExpense();
                case "list" -> listExpenses();
                case "search" -> searchExpense();
                case "filter by category" -> filterByCategory();
                case "remove" -> removeExpense();
                case "edit" -> editExpense();
                case "stats" -> showStatistics();
                case "quit" -> {
                    return;
                }
                default -> System.out.println("Invalid command");
            }
        }
    }

    private void printMenu(){
        System.out.println("""
                Commands:
                0. Help -> shows the menu again!
                1. Add -> Log a new expense!
                2. List -> List all of your expenses!
                3. Search -> Search for an element!
                4. Filter By Category -> Filters by category!
                5. Remove -> Removes an expense from your tracker!
                6. Edit -> Edit an expense!
                7. Stats -> Show statistics!
                X. Quit -> Quits the app!
                """);
        System.out.println();
    }

    private void addExpense(){
        this.expenseTracker.add(createExpense());
    }

    private void listExpenses(){
        this.expenseTracker.getExpenses().forEach(System.out::println);
    }

    private void showStatistics() {
        System.out.print("Enter month number or leave empty for total stats: ");
        String input = scanner.nextLine();
        int monthValue = input.isEmpty() ? 0 : Integer.parseInt(input);

        System.out.println("Total expenses: " + this.expenseTracker.getTotalSum(monthValue));
        System.out.println("Average expense: " + this.expenseTracker.getAverageSum(monthValue));
        System.out.println("Highest expense: " + this.expenseTracker.getHighestExpense(monthValue));
        System.out.println("Lowest expense: " + this.expenseTracker.getLowestExpense(monthValue));
        System.out.println("Number of expenses: " + this.expenseTracker.getTotalExpenses(monthValue));

    }

    private void searchExpense() {
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Expense expense = expenseTracker.searchByPrice(price);

        if (expense == null) {
            System.out.println("Expense not found.");
        } else {
            System.out.println(expense);
        }

    }

    private void filterByCategory(){
        System.out.print("Enter category: ");
        String categoryName = scanner.nextLine();

        this.expenseTracker.filterExpenses(categoryName).forEach(System.out::println);
    }

    private void removeExpense(){
        System.out.print("Enter the id of the expense you want to remove: ");
        int id = Integer.parseInt(scanner.nextLine());

        if(!this.expenseTracker.remove(id)){
            System.out.println("No such expense!");
        }
    }

    private void editExpense(){
        System.out.print("Enter the id of the expense you want to edit: ");
        int id = Integer.parseInt(scanner.nextLine());
        Expense ex = expenseTracker.searchByID(id);
        if(ex == null){
            System.out.println("No such expense!");
        }else{
            System.out.println("Enter new values when prompted (empty keeps current value)");

            System.out.print("Enter new price: ");
            String value = scanner.nextLine();
            if(!value.isEmpty()){
                double price = Double.parseDouble(value);
                ex.setPrice(price);
            }

            System.out.print("Enter new category: ");
            String categoryName = scanner.nextLine().toUpperCase();
            if(!categoryName.isEmpty()){
                Category category = Category.valueOf(categoryName);
                ex.setCategory(category);
            }

            System.out.print("Enter new date (dd.mm.yyyy): ");
            String dateString = scanner.nextLine();
            if(!dateString.isEmpty()) {
                LocalDate date = createDate(dateString);
                ex.setDate(date);
            }

            System.out.print("Enter new description: ");
            String description = scanner.nextLine();
            if(!description.isEmpty()) ex.setDescription(description);

        }
    }

    private Expense createExpense(){
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Select category: ");
        for(int i = 0; i < Category.values().length; i++){
            System.out.printf("%d: %s %n", i+1, Category.values()[i]);
        }

        System.out.print("Enter a number: ");
        Category category = Category.values()[Integer.parseInt(scanner.nextLine()) - 1];

        System.out.print("Enter date (dd.mm.yyyy): ");
        LocalDate date = createDate(scanner.nextLine());

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        return new Expense(price, category, date, description);
    }

    private LocalDate createDate(String dateString){
        String[] dateValue = dateString.split("\\.");
        return LocalDate.of(Integer.parseInt(dateValue[2]), Integer.parseInt(dateValue[1]), Integer.parseInt(dateValue[0]));
    }
}
