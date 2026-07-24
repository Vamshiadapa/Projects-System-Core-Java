package librarymanagementsystem;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String department;
    private double price;
    private int totalCopies;
    private int availableCopies;
    private boolean isIssued;
    
    public Book(String bookId, String title, String author, 
                String department, double price, int totalCopies) {
        this.bookId = bookId.toUpperCase();
        this.title = title;
        this.author = author;
        this.department = department.toUpperCase();
        this.price = price;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.isIssued = false;
    }
    
 
    public boolean isAvailable() {
        return availableCopies > 0;
    }
    
    public void issueCopy() {
        if (availableCopies > 0) {
            availableCopies--;
            if (availableCopies == 0) {
                isIssued = true;
            }
        }
    }
    
    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            isIssued = false;
        }
    }
    
    public String getBookId() {
        return bookId;
    }
    
    public void setBookId(String bookId) {
        this.bookId = bookId.toUpperCase();
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
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department.toUpperCase();
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getTotalCopies() {
        return totalCopies;
    }
    
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
        if (availableCopies > totalCopies) {
            availableCopies = totalCopies;
        }
    }
    
    public int getAvailableCopies() {
        return availableCopies;
    }
    
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    
    public boolean isIssued() {
        return isIssued;
    }
    
    public void setIssued(boolean issued) {
        isIssued = issued;
    }
    
    public String toString() {
        return String.format("| %-8s | %-30s | %-20s | %-6s | ₹%-8.2f | %-3d/%d |",
            bookId,
            title.length() > 30 ? title.substring(0, 27) + "..." : title,
            author.length() > 20 ? author.substring(0, 17) + "..." : author,
            department,
            price,
            availableCopies,
            totalCopies
        );
    }
    
    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book Details:\n");
        sb.append("  ID: ").append(bookId).append("\n");
        sb.append("  Title: ").append(title).append("\n");
        sb.append("  Author: ").append(author).append("\n");
        sb.append("  Department: ").append(department).append("\n");
        sb.append("  Price: ₹").append(String.format("%.2f", price)).append("\n");
        sb.append("  Available Copies: ").append(availableCopies).append("/").append(totalCopies);
        return sb.toString();
    }
}