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
        while(true){
            printMenu();
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            switch (command.toLowerCase()){
                case "add" -> addExpense();
                case "list" -> listExpenses();
                case "total" -> showTotal();
                case "search" -> searchExpense();
                case "filter by category" -> filterByCategory();
                case "remove" -> removeExpense();
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
                1. Add -> Log a new expense!
                2. List -> List all of your expenses!
                3. Total -> Get a total sum of all your expenses!
                4. Search -> Search for an element!
                5. Filter By Category -> Filters by category!
                6. Remove -> Removes an expense from your tracker!
                7. Quit -> Quits the app!
                """);
        System.out.println();
    }

    private void addExpense(){
        System.out.print("Enter sum: ");
        double sum = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter date (dd.mm.yyyy): ");
        String[] dateValue = scanner.nextLine().split("\\.");
        LocalDate date = LocalDate.of(Integer.parseInt(dateValue[2]), Integer.parseInt(dateValue[1]), Integer.parseInt(dateValue[0]));
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Expense ex = new Expense(sum, category, date, description);

        this.expenseTracker.add(ex);
    }

    private void listExpenses(){
        this.expenseTracker.getExpenses().forEach(System.out::println);
    }

    private void searchExpense() {

        expenseTracker.selectionSortByValue();

        System.out.print("Enter value: ");
        double value = Double.parseDouble(scanner.nextLine());

        Expense expense = expenseTracker.binarySearchByValue(value);

        if (expense == null) {
            System.out.println("Expense not found.");
        } else {
            System.out.println(expense);
        }

    }

    private void showTotal(){
        System.out.printf("The total sum of your expenses is: %.2f%n",this.expenseTracker.getTotalSum());
    }

    private void filterByCategory(){
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        this.expenseTracker.filterExpenses(category).forEach(System.out::println);
    }

    private void removeExpense(){
        System.out.print("Enter the id of the expense you want to remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        if(!this.expenseTracker.remove(id)){
            System.out.println("No such expense!");
        };
    }


}
