void main() {
    try {
        ExpenseApp app = new ExpenseApp();
        app.start();
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}