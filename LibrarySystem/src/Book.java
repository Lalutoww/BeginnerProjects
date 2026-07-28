public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private int quantity;

    public Book(String title, String author, String isbn, int quantity){
        this.title = title;
        this.author = author;
        this.quantity=quantity;
        this.isbn = isbn;
    }

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.quantity = 5;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable(){
        return this.quantity > 0;
    }

    public boolean equals(Object obj){
        if(this == obj) return true;

        if(!(obj instanceof Book compared)) return false;

        return this.title == compared.title && this.author == compared.author && this.isbn == compared.isbn;
    }

    @Override
    public String toString() {
        return title +
                " by " + author +
                " | ISBN: " + isbn +
                " | Available: " + quantity;
    }
}
