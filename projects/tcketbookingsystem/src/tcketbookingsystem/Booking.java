package tcketbookingsystem;

public class Booking {
    private String bookingId;
    private String customerName;
    private int row;
    private int col;
    private int ticketCount;
    private double totalAmount;
    private String bookingTime;
    
   
    public Booking(String bookingId, String customerName, int row, int col, int ticketCount) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.row = row;
        this.col = col;
        this.ticketCount = ticketCount;
        this.totalAmount = ticketCount * 10.0; // $10 per ticket
        this.bookingTime = java.time.LocalDateTime.now().toString();
    }
    
    public String getBookingId() {
        return bookingId;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public int getTicketCount() {
        return ticketCount;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public String getBookingTime() {
        return bookingTime;
    }
   
    @Override
    public String toString() {
        return String.format(
            "Booking ID: %s | Customer: %s | Seat: Row %d, Col %d | Tickets: %d | Total: $%.2f",
            bookingId, customerName, row, col, ticketCount, totalAmount
        );
    }
 
    public String getDetailedInfo() {
        StringBuilder info = new StringBuilder();
        info.append("========================================\n");
        info.append("       BOOKING DETAILS\n");
        info.append("========================================\n");
        info.append("Booking ID: ").append(bookingId).append("\n");
        info.append("Customer Name: ").append(customerName).append("\n");
        info.append("Seat Location: Row ").append(row).append(", Column ").append(col).append("\n");
        info.append("Number of Tickets: ").append(ticketCount).append("\n");
        info.append("Total Amount: $").append(String.format("%.2f", totalAmount)).append("\n");
        info.append("Booking Time: ").append(bookingTime).append("\n");
        info.append("Status: Confirmed\n");
        return info.toString();
    }
}