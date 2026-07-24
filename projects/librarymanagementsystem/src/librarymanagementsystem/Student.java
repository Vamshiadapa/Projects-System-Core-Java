package librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;


public class Student {
    private String studentId;
    private String name;
    private String department;
    private String mobileNumber;
    private List<IssuedBook> issuedBooks;
    
    public Student(String studentId, String name, String department, String mobileNumber) {
        this.studentId = studentId.toUpperCase();
        this.name = name;
        this.department = department.toUpperCase();
        this.mobileNumber = mobileNumber;
        this.issuedBooks = new ArrayList<>();
    }
    
  
    public void addIssuedBook(IssuedBook book) {
        if (issuedBooks.size() < 3) {
            issuedBooks.add(book);
        }
    }
    
 
    public void removeIssuedBook(String bookId) {
        issuedBooks.removeIf(issued -> 
            issued.getBook().getBookId().equalsIgnoreCase(bookId)
        );
    }
    
   
    public boolean hasBookIssued(String bookId) {
        for (IssuedBook issued : issuedBooks) {
            if (issued.getBook().getBookId().equalsIgnoreCase(bookId)) {
                return true;
            }
        }
        return false;
    }
    
    
    public int getIssuedBooksCount() {
        return issuedBooks.size();
    }
    
  
    public void displayIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println("  No books issued");
            return;
        }
        
        System.out.println("  Total: " + issuedBooks.size() + " books");
        for (IssuedBook issued : issuedBooks) {
            System.out.println("  " + issued.getShortInfo());
        }
    }
   
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId.toUpperCase();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department.toUpperCase();
    }
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public List<IssuedBook> getIssuedBooks() {
        return issuedBooks;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-20s | %-6s | %-12s | %d |",
            studentId,
            name.length() > 20 ? name.substring(0, 17) + "..." : name,
            department,
            mobileNumber,
            issuedBooks.size()
        );
    }
    
   
    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student Details:\n");
        sb.append("  ID: ").append(studentId).append("\n");
        sb.append("  Name: ").append(name).append("\n");
        sb.append("  Department: ").append(department).append("\n");
        sb.append("  Mobile: ").append(mobileNumber).append("\n");
        sb.append("  Books Issued: ").append(issuedBooks.size()).append("/3");
        return sb.toString();
    }
}