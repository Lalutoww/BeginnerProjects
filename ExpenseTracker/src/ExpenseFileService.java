import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ExpenseFileService {

    private final Path expensesFilePath;

    public ExpenseFileService() throws IOException {
        expensesFilePath = Path.of("expenses.txt");
        if(!Files.exists(expensesFilePath)){
            Files.createFile(expensesFilePath);
        }
    }

    public void save(List<Expense> expenses) throws IOException{
        List<String> expensesToFileFormat = expenses
                .stream()
                .map(Expense::toFileFormat)
                .toList();

        Files.write(this.expensesFilePath, expensesToFileFormat);

    }

    public List<Expense> load() throws IOException {
        return Files.readAllLines(this.expensesFilePath)
                .stream()
                .map(Expense::parseExpense)
                .toList();
    }
}
