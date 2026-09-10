import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExpenseTracker {
    private final List<Expense> expenses;
    private final ExpenseFileService fileService;

    public ExpenseTracker(ExpenseFileService fileService) throws IOException {
        this.fileService = fileService;
        this.expenses = new ArrayList<>(this.fileService.load());
    }

    public void add(Expense expense) throws IOException {
        this.expenses.add(expense);
        this.fileService.save(this.expenses);
    }

    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    public boolean remove(int id) throws IOException {
        Optional<Expense> expense = searchByID(id);

        if(expense.isPresent()){
            expenses.remove(expense.get());
            this.fileService.save(this.expenses);
            return true;
        }

        return false;
    }

    public boolean edit(int id, OptionalDouble price, Optional<Category> category, Optional<LocalDate> date, String description) throws IOException {
        Optional<Expense> ex = this.searchByID(id);

        if (ex.isEmpty()) {
            return false;
        }

        Expense expense = ex.get();
        price.ifPresent(expense::setPrice);
        category.ifPresent(expense::setCategory);
        date.ifPresent(expense::setDate);
        if (!description.isEmpty()) expense.setDescription(description);

        this.fileService.save(this.expenses);
        return true;
    }

    public List<Expense> filterExpenses(Category category){
        return this.expenses.stream().filter(x -> x.getCategory().equals(category)).collect(Collectors.toList());
    }

    public DoubleSummaryStatistics getMonthStatistics(int month) {
        if (isMonthInvalid(month)) {
            return new DoubleSummaryStatistics();
        }

        return expensesForMonth(month)
                .mapToDouble(Expense::getPrice)
                .summaryStatistics();
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
