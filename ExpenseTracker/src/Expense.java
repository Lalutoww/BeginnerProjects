import java.time.LocalDate;
import java.util.Objects;

public class Expense {
    private static int idCounter = 1;

    private final int id;
    private double value;
    private String category;
    private LocalDate date;
    private String description;

    public Expense(double value, String category, LocalDate date, String description) {
        this.id = idCounter++;
        setValue(value);
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public String getCategory() {
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

    public void setValue(double value) {
        this.value = Math.abs(value);
    }

    public void setCategory(String category) {
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
                id, value, date, category, description);
    }
}
