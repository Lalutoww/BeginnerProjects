import java.util.ArrayList;
import java.util.Objects;

public class Member {
    private final String name;
    private final int id;
    private final ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Member(String name, int ID) {
        this.name = name;
        this.id = ID;
    }

    public String getName(){
        return this.name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book){
            borrowedBooks.add(book);
    }

    public boolean returnBook(Book book){
        // if the book exists, therefore is removed, then return it, otherwise false
        if (borrowedBooks.remove(book)) {
            book.setQuantity(book.getQuantity()+1);
            return true;
        }

        return false;
    }

    public boolean equals(Object obj){
        if(this == obj) return true;

        if(!(obj instanceof Member compared)) return false;

        return this.id == compared.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "Member{id=" + id +
                ", name='" + name +
                "', borrowed=" + borrowedBooks.size() + "}";
    }
}
