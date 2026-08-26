import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Optional<Expense> expense = searchByID(id);

        if(expense.isPresent()){
            expenses.remove(expense.get());
            return true;
        }

        return false;
    }

    public List<Expense> filterExpenses(Category category){
        return this.expenses.stream().filter(x -> x.getCategory().equals(category)).collect(Collectors.toList());
    }

    public double getTotalSum(int month){
        if(isMonthInvalid(month)){
            return 0.00;
        }
        return expensesForMonth(month).mapToDouble(Expense::getPrice).sum();
    }

    public double getAverageSum(int month){
        if(isMonthInvalid(month)){
            return 0.00;
        }
        return expensesForMonth(month).mapToDouble(Expense::getPrice).average().orElse(0.00);
    }

    public double getHighestExpense(int month){
        if(isMonthInvalid(month)){
            return 0.00;
        }

        return expensesForMonth(month).mapToDouble(Expense::getPrice).max().orElse(0.00);
    }

    public double getLowestExpense(int month){
        if(isMonthInvalid(month)){
            return 0.00;
        }
        return expensesForMonth(month).mapToDouble(Expense::getPrice).min().orElse(0.00);
    }

    public int getTotalExpenses(int month){
        if(isMonthInvalid(month)){
            return 0;
        }

        return (int) expensesForMonth(month).count();
    }

    public List<Expense> searchByPrice(double price){
        return this.expenses.stream().filter(x -> Math.abs(x.getPrice() - price) < 0.001).collect(Collectors.toList());
    }

    public Optional<Expense> searchByID(int id){
        return this.expenses.stream().filter(x -> x.getId() == id).findFirst();
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
