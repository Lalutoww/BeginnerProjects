import java.time.LocalDate;
import java.util.Objects;

public class Expense implements Comparable<Expense>{
    private static int idCounter = 1;

    private final int id;
    private double price;
    private Category category;
    private LocalDate date;
    private String description;

    public Expense(double price, Category category, LocalDate date, String description) {
        this.id = idCounter++;
        setPrice(price);
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Expense expense = (Expense) other;
        return id == expense.id;
    }

    public void setPrice(double price) {
        this.price = Math.abs(price);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Amount: %.2f | Date: %s | Category: %s | Description: %s",
                id, price, date, category, description);
    }

    @Override
    public int compareTo(Expense other) {
        int dateComparison = this.getDate().compareTo(other.getDate());

        if(dateComparison == 0){
            int priceComparison = Double.compare(this.price, other.price);

            if(priceComparison == 0){
                return Integer.compare(this.id, other.id);
            }
            return priceComparison;
        }
        return dateComparison;
    }
}
