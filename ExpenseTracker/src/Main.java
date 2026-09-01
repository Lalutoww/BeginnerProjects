void main() {
    //ExpenseApp app = new ExpenseApp();
    //app.start();

    Expense original = new Expense(15.5, Category.FOOD, LocalDate.of(2026,9, 1), "Lunch");
    String text = original.toFileFormat();

    Expense parsed = Expense.parseExpense(text);


    System.out.println("Original: " + original);
    System.out.println("Parsed: " + parsed);
    System.out.println(original.equals(parsed));


}