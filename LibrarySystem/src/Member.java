import java.util.ArrayList;

public class Member {
    private final String name;
    private final int ID;
    private final ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Member(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book){
            borrowedBooks.add(book);
    }

    public void returnBook(Book book){
        // if the book exists, therefore is removed, then return it, otherwise false
        if (borrowedBooks.remove(book)) {
            book.setQuantity(book.getQuantity()+1);
            return;
        }
        System.out.println("This member hasn't borrowed this book.");
    }

    @Override
    public String toString() {
        return "Member{id=" + ID +
                ", name='" + name +
                "', borrowed=" + borrowedBooks.size() + "}";
    }
}
