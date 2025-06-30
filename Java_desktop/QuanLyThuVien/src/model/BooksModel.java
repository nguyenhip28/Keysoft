package model;

public class BooksModel {

    private int bookId;
    private String title;
    private String author;
    private int publishYear;
    private String category;
    private int quantity;
    private String imageUrl;

    // Constructor đầy đủ (dùng khi lấy dữ liệu từ DB có cả bookId)
    public BooksModel(int bookId, String title, String author, int publishYear, String category, int quantity, String imageUrl) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.category = category;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    // Constructor không có ID (dùng khi thêm sách mới, ID tự tăng)
    public BooksModel(String title, String author, int publishYear, String category, int quantity, String imageUrl) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.category = category;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    // Getter và Setter
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
