package librarymanagementsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {
    
    // Fine rates
    private static final double DAILY_FINE_RATE = 10.0; // ₹10 per day
    private static final double PROCESSING_CHARGE = 100.0; // ₹100 processing charge
    private static final int FREE_LOAN_PERIOD = 15; // 15 days free
    
    public static double calculateOverdueFine(LocalDate issueDate,
            LocalDate returnDate) {

if (returnDate == null) {
returnDate = LocalDate.now();
}

long totalDays = ChronoUnit.DAYS.between(issueDate, returnDate);

if (totalDays <= FREE_LOAN_PERIOD) {
return 0;
}

long overdueDays = totalDays - FREE_LOAN_PERIOD;

return overdueDays * DAILY_FINE_RATE;
}
    public static double calculateOverdueFine(IssuedBook issuedBook) {
        return issuedBook.calculateFine();
    }
  
    public static double calculateLostBookAmount(Book book) {
        return book.getPrice() + PROCESSING_CHARGE;
    }
  
    public static double calculateTotalDue(IssuedBook issuedBook) {
        return issuedBook.calculateFine();
    }
    
    public static double calculateTotalDueForLost(IssuedBook issuedBook) {
        return issuedBook.calculateLostBookAmount();
    }
    
    public static long getDaysOverdue(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate == null) {
            returnDate = LocalDate.now();
        }
        
        if (returnDate.isBefore(dueDate)) {
            return 0;
        }
        
        return ChronoUnit.DAYS.between(dueDate, returnDate);
    }
    
    public static boolean isOverdue(LocalDate dueDate, LocalDate returnDate) {
        LocalDate checkDate = returnDate != null ? returnDate : LocalDate.now();
        return checkDate.isAfter(dueDate);
    }
    
    public static String formatFine(double amount) {
        return String.format("₹%.2f", amount);
    }
}