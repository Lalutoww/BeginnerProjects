import java.util.Comparator;

public class sortByAmount implements Comparator<Expense> {

    @Override
    public int compare(Expense o1, Expense o2) {
        return Double.compare(o1.getValue(), o2.getValue());
    }
}
