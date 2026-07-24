package librarymanagementsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class IssuedBook {
    private Book book;
    private Student student;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private static final int LOAN_PERIOD_DAYS = 15;
    
    public IssuedBook(Book book, Student student) {
        this.book = book;
        this.student = student;
        this.issueDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(LOAN_PERIOD_DAYS);
        this.returnDate = null;
    }
    
    public boolean isOverdue() {
        if (returnDate != null) {
            return returnDate.isAfter(dueDate);
        }
        return LocalDate.now().isAfter(dueDate);
    }
    
    public long getDaysOverdue() {
        LocalDate today = returnDate != null ? returnDate : LocalDate.now();
        if (today.isBefore(dueDate)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dueDate, today);
    }
    
   
    public double calculateFine() {
        long daysOverdue = getDaysOverdue();
        if (daysOverdue <= 0) {
            return 0;
        }
        return daysOverdue * 10.0; // ₹10 per day
    }
    
   
    public double calculateLostBookAmount() {
        return book.getPrice() + 100.0; // Book price + ₹100 processing charge
    }
    

    public void returnBook() {
        this.returnDate = LocalDate.now();
        book.returnCopy();
    }
    
    public Book getBook() {
        return book;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public LocalDate getIssueDate() {
        return issueDate;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
  
    public String getShortInfo() {
        return String.format("Book: %s (%s) | Issued: %s | Due: %s",
            book.getTitle(),
            book.getBookId(),
            issueDate,
            dueDate
        );
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Issue Record:\n");
        sb.append("  Student: ").append(student.getName()).append(" (").append(student.getStudentId()).append(")\n");
        sb.append("  Department: ").append(student.getDepartment()).append("\n");
        sb.append("  Book: ").append(book.getTitle()).append(" (").append(book.getBookId()).append(")\n");
        sb.append("  Book Price: ₹").append(String.format("%.2f", book.getPrice())).append("\n");
        sb.append("  Issue Date: ").append(issueDate).append("\n");
        sb.append("  Due Date: ").append(dueDate).append("\n");
        
        if (returnDate != null) {
            sb.append("  Return Date: ").append(returnDate).append("\n");
            if (isOverdue()) {
                sb.append("  ⚠️ OVERDUE by ").append(getDaysOverdue()).append(" days\n");
                sb.append("  Fine: ₹").append(String.format("%.2f", calculateFine()));
            }
        } else {
            sb.append("  Status: Currently Issued");
        }
        
        return sb.toString();
    }
}