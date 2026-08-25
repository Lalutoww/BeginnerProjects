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

    public Expense searchByValue(double value){
        return this.expenses.stream().filter(x -> x.getValue() == value).findFirst().orElse(null);
    }
}
