import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseTracker {
    private final List<Expense> expenses;

    public ExpenseTracker(){
        this.expenses = new ArrayList<>();
    }

    public void add(Expense expense){
        this.expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    public boolean remove(int id){
        return expenses.removeIf(e -> e.getId() == id);
    }

    public List<Expense> filterExpenses(String category){
        return this.expenses.stream().filter(x -> x.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    public double getTotalSum(){
        double sum = 0;
        for(Expense ex : expenses){
            sum+= ex.getValue();
        }
        return sum;
    }

    public double getAverageSum(){
        return this.expenses.stream().mapToDouble(Expense::getValue).average().orElse(0.00);
    }

    public double getMostExpensiveSum(){
        return this.expenses.stream().sorted(new sortByAmount()).mapToDouble(Expense::getValue).max().orElse(0.00);
    }

    public double getLeastExpensiveSum(){
        return this.expenses.stream().sorted(new sortByAmount()).mapToDouble(Expense::getValue).min().orElse(0.00);
    }

    public Expense searchByValue(double value){
        return this.expenses.stream().filter(x -> x.getValue() == value).findFirst().orElse(null);
    }

    public Expense searchByID(int id){
        return this.expenses.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}
