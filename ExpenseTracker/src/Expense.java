import java.time.LocalDate;
import java.util.Objects;

public class Expense {
    private static int idCounter = 1;

    private final int id;
    private final double value;
    private final String category;
    private final LocalDate date;
    private final String description;

    public Expense(double value, String category, LocalDate date, String description) {
        this.id = idCounter++;
        this.value = Math.abs(value);
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return id == expense.id;
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
