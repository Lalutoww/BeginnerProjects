import java.util.Scanner;

public class LibraryApp {
    private final Library library = new Library();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        loadSampleData();

        boolean running = true;

        while(running){
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice){
                case 1 -> showBooks();
                case 2 -> showMembers();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> findBookByTitle();
                case 6 -> findBookByISBN();
                case 7 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void findBookByTitle() {
        System.out.print("Book title: ");
        Book book = library.findBookByTitle(scanner.nextLine());
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book not found.");
        }
    }

    private void findBookByISBN() {
        System.out.print("Book ISBN: ");
        Book book = library.findBookByISBN(scanner.nextLine());
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book not found.");
        }
    }

    private void printMenu(){
        System.out.println("""
                ===== LIBRARY =====
                
                1. Show books
                2. Show members
                3. Borrow book
                4. Return book
                5. Find book by title
                6. Find book by ISBN
                7. Exit
                
                Choice:""");
    }

    private void loadSampleData() {
        library.addBook(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "9780134685991", 1));

        library.registerMember("Martin");
        library.registerMember("Ivan");
    }

    private void showBooks() {
        for(Book book : library.getBooks()) {
            System.out.println(book);
        }
    }

    private void showMembers(){
        for(Member member : library.getMembers()){
            System.out.println(member);
        }
    }

    private void borrowBook() {

        System.out.print("Member ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Book title: ");
        String title = scanner.nextLine();

        boolean success = library.borrowBook(id, title);

        if(success)
            System.out.println("Book borrowed successfully.");
        else
            System.out.println("Borrowing failed.");

    }

    private void returnBook() {

        System.out.print("Member ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Book title: ");
        String title = scanner.nextLine();

        boolean success = library.returnBook(id, title);

        if(success)
            System.out.println("Book returned.");
        else
            System.out.println("Return failed.");

    }
}
