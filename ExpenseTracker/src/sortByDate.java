import java.util.Comparator;

public class sortByDate implements Comparator<Expense> {

    @Override
    public int compare(Expense o1, Expense o2) {
        if(o1.getDate().isBefore(o2.getDate())){
            return -1;
        }
        if(o1.getDate().equals(o2.getDate())){
            return 0;
        }
        return 1;
    }
}
