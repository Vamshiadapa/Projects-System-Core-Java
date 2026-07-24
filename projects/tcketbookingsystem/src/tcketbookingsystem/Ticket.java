package tcketbookingsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private String ticketId;
    private String bookingId;
    private String customerName;
    private int row;
    private int col;
    private double price;
    private String showTime;
    private String ticketType;
    private LocalDateTime purchaseTime;
    
   
    public Ticket(String bookingId, String customerName, int row, int col) {
        this.ticketId = generateTicketId();
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.row = row;
        this.col = col;
        this.price = 10.0;
        this.showTime = "7:00 PM";
        this.ticketType = "Standard";
        this.purchaseTime = LocalDateTime.now();
    }
    
   
    private String generateTicketId() {
        return "TKT" + System.currentTimeMillis() + String.format("%02d", (int)(Math.random() * 100));
    }
    
    public String getTicketId() {
        return ticketId;
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
    
    public double getPrice() {
        return price;
    }
    
    public String getShowTime() {
        return showTime;
    }
    
    public String getTicketType() {
        return ticketType;
    }
    
    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
    
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
  
    public String printTicket() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StringBuilder ticket = new StringBuilder();
        ticket.append("\n");
        ticket.append("           MOVIE TICKET\n");
        ticket.append("========================================\n");
        ticket.append("Ticket ID: ").append(ticketId).append("\n");
        ticket.append("Booking ID: ").append(bookingId).append("\n");
        ticket.append("Customer: ").append(customerName).append("\n");
        ticket.append("Seat: Row ").append(row).append(", Col ").append(col).append("\n");
        ticket.append("Show Time: ").append(showTime).append("\n");
        ticket.append("Ticket Type: ").append(ticketType).append("\n");
        ticket.append("Price: $").append(String.format("%.2f", price)).append("\n");
        ticket.append("Purchase Time: ").append(purchaseTime.format(formatter)).append("\n");
        ticket.append("        Thank you for booking!\n");
        return ticket.toString();
    }
    
    public String toString() {
        return String.format(
            "Ticket[ID: %s, Booking: %s, Customer: %s, Seat: Row %d-Col %d, Price: $%.2f]",
            ticketId, bookingId, customerName, row, col, price
        );
    }
}