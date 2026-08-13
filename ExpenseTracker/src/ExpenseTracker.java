import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseTracker {
    private List<Expense> expenses;

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

    public void selectionSortByValue(){
        for (int i = 0; i < expenses.size() - 1; i++) {

            int min = i;

            for (int j = i + 1; j < expenses.size(); j++) {

                if(expenses.get(j).getValue() < expenses.get(min).getValue()) {
                    min = j;
                }

            }

            if(min != i) {
                Expense temp = expenses.get(i);
                expenses.set(i, expenses.get(min));
                expenses.set(min, temp);
            }
        }
    }

    public Expense binarySearchByValue(double value) {

        int left = 0;
        int right = expenses.size() - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            Expense current = expenses.get(middle);

            if (Math.abs(current.getValue() - value) < 0.0001) {
                return current;
            }

            if (current.getValue() < value) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return null;
    }
}
