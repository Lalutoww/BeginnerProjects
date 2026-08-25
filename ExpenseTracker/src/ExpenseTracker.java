import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Expense expense = searchByID(id);

        if(expense != null){
            expenses.remove(expense);
            return true;
        }

        return false;
    }

    public List<Expense> filterExpenses(String category){
        return this.expenses.stream().filter(x -> x.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    public double getTotalSum(int month){
        if(isMonthInvalid(month)){
            return 0.00;
        }
        return expensesForMonth(month).mapToDouble(Expense::getValue).sum();
    }

    public double getAverageSum(int month){
        if(isMonthInvalid(month)){
            return 0.00;
        }
        return expensesForMonth(month).mapToDouble(Expense::getValue).average().orElse(0.00);
    }

    public double getHighestExpense(int month){
        if(isMonthInvalid(month)){
            return 0.00;
        }

        return expensesForMonth(month).mapToDouble(Expense::getValue).max().orElse(0.00);
    }

    public double getLowestExpense(int month){
        if(isMonthInvalid(month)){
            return 0.00;
        }
        return expensesForMonth(month).mapToDouble(Expense::getValue).min().orElse(0.00);
    }

    public int getTotalExpenses(int month){
        if(isMonthInvalid(month)){
            return 0;
        }

        return (int) expensesForMonth(month).count();
    }

    public Expense searchByValue(double value){
        return this.expenses.stream().filter(x -> x.getValue() == value).findFirst().orElse(null);
    }

    public Expense searchByID(int id){
        return this.expenses.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    private Stream<Expense> expensesForMonth(int month){

        if(month == 0){
            return expenses.stream();
        }

        return expenses.stream()
                .filter(e -> e.getDate().getMonthValue()==month);
    }

    private boolean isMonthInvalid(int month){
        return month < 0 || month > 12;
    }
}
