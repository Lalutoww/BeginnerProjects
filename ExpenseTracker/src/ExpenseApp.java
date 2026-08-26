import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class ExpenseApp {
    private final ExpenseTracker expenseTracker;
    private final InputReader inputReader;

    public ExpenseApp() {
        this.expenseTracker = new ExpenseTracker();
        this.inputReader = new InputReader();
    }

    public void start() {
        printMenu();
        while (true) {
            String command = inputReader.readString("Enter command: ");

            switch (command.toLowerCase()) {
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

    private void addExpense() {
        this.expenseTracker.add(createExpense());
    }

    private void listExpenses() {
        this.expenseTracker.getExpenses().forEach(System.out::println);
    }

    private void showStatistics() {

        OptionalInt month = inputReader.readOptionalInt("Enter month number or leave empty for total stats: ");
        int monthValue = month.isPresent()
                ? month.getAsInt()
                : 0;

        System.out.println("Total expenses: " + this.expenseTracker.getTotalSum(monthValue));
        System.out.println("Average expense: " + this.expenseTracker.getAverageSum(monthValue));
        System.out.println("Highest expense: " + this.expenseTracker.getHighestExpense(monthValue));
        System.out.println("Lowest expense: " + this.expenseTracker.getLowestExpense(monthValue));
        System.out.println("Number of expenses: " + this.expenseTracker.getTotalExpenses(monthValue));

    }

    private void searchExpense() {
        double price = inputReader.readDouble("Enter price: ");

        List<Expense> expenses = expenseTracker.searchByPrice(price);

        if (!expenses.isEmpty()) {
            expenses.forEach(System.out::println);
        } else {
            System.out.println("Expense not found.");
        }

    }

    private void filterByCategory() {
        Category category = inputReader.readCategory("Enter category: ");

        this.expenseTracker.filterExpenses(category).forEach(System.out::println);
    }

    private void removeExpense() {
        if (!this.expenseTracker.remove(inputReader.readInt("Enter the id of the expense you want to remove: "))) {
            System.out.println("No such expense!");
        }
    }

    private void editExpense() {
        Optional<Expense> ex = expenseTracker.searchByID(inputReader.readInt("Enter the id of the expense you want to edit: "));

        if (ex.isEmpty()) {
            System.out.println("No such expense!");
            return;
        }

        Expense expense = ex.get();
        System.out.println("Enter new values when prompted (empty keeps current value)");

        OptionalDouble price = inputReader.readOptionalDouble("Enter new price: ");
        price.ifPresent(expense::setPrice);

        Optional<Category> categoryValue = inputReader.readOptionalCategory("Select new category: ");
        categoryValue.ifPresent(expense::setCategory);

        Optional<LocalDate> optionalDate = inputReader.readOptionalDate("Enter new date (dd.mm.yyyy): ");
        optionalDate.ifPresent(expense::setDate);

        String description = inputReader.readString("Enter new description: ");
        if (!description.isEmpty()) expense.setDescription(description);

    }

    private Expense createExpense() {
        double price = inputReader.readDouble("Enter price: ");
        Category category = inputReader.readCategory("Select category ");
        LocalDate date = inputReader.readDate("Enter date (dd.mm.yyyy): ");
        String description = inputReader.readString("Enter description: ");

        return new Expense(price, category, date, description);
    }

    private void printMenu() {
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
}
