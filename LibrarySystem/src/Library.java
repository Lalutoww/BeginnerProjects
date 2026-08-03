import java.util.ArrayList;
import java.util.List;

public class Library {
    private static int currentId = 1;
    private final ArrayList<Member> members;
    private final ArrayList<Book> books;

    public Library() {
        this.members = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public List<Book> getBooks() {
        return books;
    }

    public boolean addBook(Book book) {
        if(findBookByISBN(book.getIsbn()) != null)
            return false;

        books.add(book);
        return true;
    }

    public Member registerMember(String name) {
        Member member = new Member(name, currentId++);
        members.add(member);
        return member;
    }

    public Book findBookByTitle(String title) {
        for (Book book : this.books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }

        return null;
    }

    public Book findBookByISBN(String isbn) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }

    public Member findMember(int id) {
        for (Member member : this.members) {
            if (member.getId() == id) {
                return member;
            }
        }

        return null;
    }

    public boolean borrowBook(int memberId, String title) {
        Member member = findMember(memberId);
        if (member == null) return false;

        Book book = findBookByTitle(title);
        if (book == null) return false;

        //Check if the book has enough quantity
        if (!book.isAvailable()) return false;

        member.borrowBook(book);
        book.setQuantity(book.getQuantity() - 1);
        return true;
    }

    public boolean returnBook(int memberId, String title) {
        Member member = findMember(memberId);
        if (member == null) return false;

        Book book = findBookByTitle(title);
        if (book == null) return false;

        //Try returning the book
        return member.returnBook(book);
    }

    @Override
    public String toString() {
        return "Library{" + "books=" + books.size() + ", members=" + members.size() + '}';
    }
}
