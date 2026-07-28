import java.util.ArrayList;

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

    public void returnBook(Book book){
        // if the book exists, therefore is removed, then return it, otherwise false
        if (borrowedBooks.remove(book)) {
            book.setQuantity(book.getQuantity()+1);
            return;
        }
        System.out.println("This member hasn't borrowed this book.");
    }

    public boolean equals(Object obj){
        if(this == obj) return true;

        if(!(obj instanceof Member compared)) return false;

        return this.name == compared.name && this.id == compared.id;
    }

    @Override
    public String toString() {
        return "Member{id=" + id +
                ", name='" + name +
                "', borrowed=" + borrowedBooks.size() + "}";
    }
}
