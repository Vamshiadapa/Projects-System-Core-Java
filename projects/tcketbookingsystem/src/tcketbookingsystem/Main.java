package tcketbookingsystem;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Theatre theatre;
    
    public static void main(String[] args) {

        theatre = new Theatre(5, 8);
        
        System.out.println("========================================");
        System.out.println("  WELCOME TO CINEMA BOOKING SYSTEM");
        System.out.println("========================================");
        
        boolean exit = false;
        
        while (!exit) {
            displayMainMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    viewAvailableSeats();
                    break;
                case 2:
                    bookTickets();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    viewBookingDetails();
                    break;
                case 5:
                    viewStatistics();
                    break;
                case 6:
                    exit = true;
                    System.out.println("\nThank you for using Cinema Booking System!");
                    System.out.println("Have a great day!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select option between 1-6.");
            }
        }
        scanner.close();
    }
  
    private static void displayMainMenu() {
       System.out.println("           MAIN MENU");
        System.out.println("1. View Available Seats");
        System.out.println("2. Book Tickets");
        System.out.println("3. Cancel Booking");
        System.out.println("4. View Booking Details");
        System.out.println("5. View Statistics");
        System.out.println("6. Exit");
        System.out.print("\nEnter your choice: ");
    }
    
   
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
  
    private static void viewAvailableSeats() {
         System.out.println("         AVAILABLE SEATS");
        theatre.displaySeats();
        System.out.println("\nAvailable seats: " + theatre.getAvailableSeatsCount());
    }
    
   
    private static void bookTickets() {
        System.out.println("           BOOK TICKETS");
    
        
     
        theatre.displaySeats();
        
        if (theatre.isFullyBooked()) {
            System.out.println("\nSorry! Theatre is fully booked. Please try another show.");
            return;
        }
        
   
        System.out.print("\nEnter your name: ");
        String customerName = scanner.nextLine();
        
        if (customerName.trim().isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }
      
        System.out.print("Enter row number (1-" + theatre.getRows() + "): ");
        int row = getUserChoice();
        System.out.print("Enter column number (1-" + theatre.getCols() + "): ");
        int col = getUserChoice();
        
     
        if (!theatre.isValidSeat(row, col)) {
            System.out.println("Invalid seat selection!");
            return;
        }
        
        if (theatre.isSeatBooked(row, col)) {
            System.out.println("Seat is already booked! Please choose another seat.");
            return;
        }
      
        System.out.print("Enter number of tickets: ");
        int ticketCount = getUserChoice();
        
        if (ticketCount <= 0) {
            System.out.println("Invalid number of tickets!");
            return;
        }
        
        boolean bookingSuccess = theatre.bookSeat(row, col, customerName, ticketCount);
        
        if (bookingSuccess) {
            System.out.println("         BOOKING CONFIRMED!");
            System.out.println("\n");
            System.out.println("✓ Booking ID: " + theatre.getLatestBookingId());
            System.out.println("✓ Customer: " + customerName);
            System.out.println("✓ Seat: Row " + row + ", Column " + col);
            System.out.println("✓ Tickets: " + ticketCount);
            System.out.println("✓ Total Amount: $" + (ticketCount * 10.0));
            System.out.println("✓ Status: Confirmed");
        } else {
            System.out.println("\nBooking failed! Please try again.");
        }
    }
    

    private static void cancelBooking() {
        System.out.println("          CANCEL BOOKING");
        
        if (theatre.getBookingsCount() == 0) {
            System.out.println("No bookings found to cancel!");
            return;
        }
        
        System.out.print("Enter Booking ID to cancel: ");
        String bookingId = scanner.nextLine().trim();
        
        if (bookingId.isEmpty()) {
            System.out.println("Booking ID cannot be empty!");
            return;
        }
        
        boolean cancellationSuccess = theatre.cancelBooking(bookingId);
        
        if (cancellationSuccess) {
 
            System.out.println("         BOOKING CANCELLED!");
            System.out.println("                   ");
            System.out.println("✓ Booking ID: " + bookingId + " has been cancelled.");
            System.out.println("✓ Refund processed successfully.");
        } else {
            System.out.println("Invalid Booking ID! Please check and try again.");
        }
    }
    
  
    private static void viewBookingDetails() {
     
        System.out.println("         BOOKING DETAILS");
      
        if (theatre.getBookingsCount() == 0) {
            System.out.println("No bookings available!");
            return;
        }
        
        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine().trim();
        
        if (bookingId.isEmpty()) {
            System.out.println("Booking ID cannot be empty!");
            return;
        }
        
        String bookingDetails = theatre.getBookingDetails(bookingId);
        
        if (bookingDetails != null) {
            System.out.println("\n" + bookingDetails);
        } else {
            System.out.println("Booking not found with ID: " + bookingId);
        }
    }
   
    private static void viewStatistics() {
        System.out.println("        THEATRE STATISTICS");
        System.out.println(theatre.getStatistics());
    }
}