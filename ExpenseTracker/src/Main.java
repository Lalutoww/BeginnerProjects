void main() {
    //ExpenseApp app = new ExpenseApp();
    //app.start();


    ExpenseFileService service = null;
    try {
        service = new ExpenseFileService();
    } catch (IOException e) {
        System.out.println(e.getMessage());
        return;
    }

    List<Expense> expenses = new ArrayList<>();
    expenses.add(new Expense(10, Category.FOOD, LocalDate.of(2026,10,11), "first"));
    expenses.add(new Expense(20, Category.EDUCATION, LocalDate.of(2025,11,11), "second"));
    expenses.add(new Expense(30, Category.HEALTH, LocalDate.of(2022,12,7), "third"));
    expenses.add(new Expense(40, Category.FOOD, LocalDate.of(2023,5,13), "fourth"));
    expenses.add(new Expense(50, Category.FOOD, LocalDate.of(2026,10,12), "fifth"));

    try {
        service.save(expenses);
        System.out.println("Successfully saved the file!");

        service.load().forEach(System.out::println);
        System.out.println("Successfully loaded the file!");
        System.out.println(new Expense(50, Category.FOOD, LocalDate.of(2026,10,12), "sixth"));
    } catch (IOException e) {
        System.out.println("Couldn't finish the program!");
    }

}