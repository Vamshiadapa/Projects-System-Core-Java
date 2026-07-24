package tcketbookingsystem;

import java.util.HashMap;
import java.util.Map;

public class Theatre {
    private int rows;
    private int cols;
    private boolean[][] seats;  // false = available, true = booked
    private Map<String, Booking> bookings;
    private int bookingCounter;
    private static final double TICKET_PRICE = 10.0;
    
    
    public Theatre(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.seats = new boolean[rows][cols];
        this.bookings = new HashMap<>();
        this.bookingCounter = 0;
        initializeSeats();
    }
    
  
    private void initializeSeats() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = false;
            }
        }
    }
    
    public void displaySeats() {
        System.out.println("\nSeat Layout (Rows " + rows + " x Columns " + cols + "):");
        System.out.println("    1  2  3  4  5  6  7  8  9 10 11 12");
        System.out.println("    -----------------------------------");
        
        for (int i = 0; i < rows; i++) {
            System.out.print("Row " + (i + 1) + " | ");
            for (int j = 0; j < cols; j++) {
                if (!seats[i][j]) {
                    System.out.print("□  "); // Available
                } else {
                    System.out.print("■  "); // Booked
                }
            }
            System.out.println();
        }
        System.out.println("\nLegend: □ = Available, ■ = Booked");
    }

    public boolean isSeatBooked(int row, int col) {
        if (!isValidSeat(row, col)) {
            return false;
        }
        return seats[row - 1][col - 1];
    }
    
  
    public boolean bookSeat(int row, int col, String customerName, int ticketCount) {
        if (!isValidSeat(row, col) || isSeatBooked(row, col)) {
            return false;
        }
        
       
        seats[row - 1][col - 1] = true;
        
      
        bookingCounter++;
        String bookingId = "BK" + String.format("%04d", bookingCounter);
        Booking booking = new Booking(bookingId, customerName, row, col, ticketCount);
        bookings.put(bookingId, booking);
        
        return true;
    }
    
    public boolean cancelBooking(String bookingId) {
        if (!bookings.containsKey(bookingId)) {
            return false;
        }
        
        Booking booking = bookings.get(bookingId);
        int row = booking.getRow();
        int col = booking.getCol();
        
      
        if (isValidSeat(row, col)) {
            seats[row - 1][col - 1] = false;
        }
        
    
        bookings.remove(bookingId);
        return true;
    }
   
    public String getBookingDetails(String bookingId) {
        if (!bookings.containsKey(bookingId)) {
            return null;
        }
        
        Booking booking = bookings.get(bookingId);
        return booking.toString();
    }
    
   
    public int getBookingsCount() {
        return bookings.size();
    }
    
   
    public String getLatestBookingId() {
        if (bookings.isEmpty()) {
            return null;
        }
        return "BK" + String.format("%04d", bookingCounter);
    }
    
   
    public int getAvailableSeatsCount() {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!seats[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
    
    
    public boolean isFullyBooked() {
        return getAvailableSeatsCount() == 0;
    }
    
   
    public boolean isValidSeat(int row, int col) {
        return row >= 1 && row <= rows && col >= 1 && col <= cols;
    }
    

    public String getStatistics() {
        int totalSeats = rows * cols;
        int availableSeats = getAvailableSeatsCount();
        int bookedSeats = totalSeats - availableSeats;
        double occupancyRate = (double) bookedSeats / totalSeats * 100;
        
        StringBuilder stats = new StringBuilder();
        stats.append("Theatre Statistics:\n");
        stats.append("------------------------\n");
        stats.append("Total Seats: ").append(totalSeats).append("\n");
        stats.append("Available Seats: ").append(availableSeats).append("\n");
        stats.append("Booked Seats: ").append(bookedSeats).append("\n");
        stats.append("Occupancy Rate: ").append(String.format("%.2f%%", occupancyRate)).append("\n");
        stats.append("Total Bookings: ").append(bookings.size()).append("\n");
        stats.append("Ticket Price: $").append(TICKET_PRICE).append("\n");
        stats.append("------------------------\n");
        
        if (!bookings.isEmpty()) {
            stats.append("\nCurrent Bookings:\n");
            for (Map.Entry<String, Booking> entry : bookings.entrySet()) {
                stats.append(entry.getValue().toString()).append("\n");
            }
        }
        
        return stats.toString();
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public double getTicketPrice() {
        return TICKET_PRICE;
    }
}