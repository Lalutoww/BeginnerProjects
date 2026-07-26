void main() {

    Library library = new Library();

    // Adding books
    library.addBook(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2));
    library.addBook(new Book("Effective Java", "Joshua Bloch", "9780134685991", 1));
    library.addBook(new Book("Java: The Complete Reference", "Herbert Schildt", "9781260463415"));

    // Member Registration
    library.registerMember("Martin");
    library.registerMember("Ivan");

    System.out.println("=== Library ===");
    System.out.println(library);

    System.out.println("\n=== Books ===");
    for (Book book : library.getBooks()) {
        System.out.println(book);
    }

    IO.println("\n=== Members ===");
    for (Member member : library.getMembers()) {
        System.out.println(member);
    }

    // Borrowing
    System.out.println("\n--- Borrowing books ---");
    library.borrowBook(1, "Clean Code");
    library.borrowBook(2, "Clean Code");

    // No more books
    library.borrowBook(1, "Clean Code");

    // Non-existing book
    library.borrowBook(1, "Unknown Book");

    // Non-existing member
    library.borrowBook(10, "Effective Java");

    System.out.println("\n=== Books after borrowing ===");
    for (Book book : library.getBooks()) {
        IO.println(book);
    }

    // Book return
    IO.println("\n--- Returning books ---");
    library.returnBook(1, "Clean Code");

    // Trying to return the book a second time
    library.returnBook(1, "Clean Code");

    // Non-existing member
    library.returnBook(100, "Effective Java");

    // Non-existing book
    library.returnBook(2, "Some Book");

    System.out.println("\n=== Final books ===");
    for (Book book : library.getBooks()) {
        System.out.println(book);
    }

    System.out.println("\n=== Members ===");
    for (Member member : library.getMembers()) {
        System.out.println(member);
        System.out.println("Borrowed books: " + member.getBorrowedBooks());
        System.out.println();
    }

    // Searching
    System.out.println("Search by title:");
    System.out.println(library.findBookByTitle("Effective Java"));

    System.out.println("\nSearch by ISBN:");
    System.out.println(library.findBookByISBN("9780132350884"));
}