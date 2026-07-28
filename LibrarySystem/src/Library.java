import java.util.ArrayList;

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

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void registerMember(String name) {
        Member member = new Member(name, currentId++);
        members.add(member);
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

    private Member findMember(int id) {
        for (Member member : this.members) {
            if (member.getId() == id) {
                return member;
            }
        }

        return null;
    }

    public void borrowBook(int memberId, String title) {
        Member member = findMember(memberId);
        if (member == null) {
            System.out.println("No such member exists!");
            return;
        }

        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("We don't have that book, sorry!");
            return;
        }

        //Check if the book has enough quantity
        if (!book.isAvailable()) {
            System.out.println("All copies of " + book.getTitle() + " are already borrowed!");
            return;
        }

        member.borrowBook(book);
        book.setQuantity(book.getQuantity() - 1);
    }

    public void returnBook(int memberId, String title) {
        Member member = findMember(memberId);
        if (member == null) {
            System.out.println("No such member.");
            return;
        }

        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("No such book.");
            return;
        }

        //Try returning the book
        member.returnBook(book);
    }

    @Override
    public String toString() {
        return "Library{" + "books=" + books.size() + ", members=" + members.size() + '}';
    }
}
