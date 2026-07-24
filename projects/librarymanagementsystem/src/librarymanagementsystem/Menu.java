package librarymanagementsystem;

import java.util.Scanner;
import java.util.List;

public class Menu {
    private Scanner scanner;
    private Library library;
 
    public Menu(Library library) {
        this.scanner = new Scanner(System.in);
        this.library = library;
    }
    
 
    public void displayMainMenu() {
        boolean exit = false;
        
        while (!exit) {
            printHeader();
            System.out.println("1. Register Student");
            System.out.println("2. Add Book");
            System.out.println("3. Display Books");
            System.out.println("4. Search Book");
            System.out.println("5. Search Department Books");
            System.out.println("6. Display Registered Students");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. Lost Book");
            System.out.println("10. Display Issued Books");
            System.out.println("11. Remove Book");
            System.out.println("12. Exit");
            System.out.print("\nEnter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    library.displayAllBooks();
                    break;
                case 4:
                    searchBook();
                    break;
                case 5:
                    searchDepartmentBooks();
                    break;
                case 6:
                    library.displayStudents();
                    break;
                case 7:
                    issueBook();
                    break;
                case 8:
                    returnBook();
                    break;
                case 9:
                    lostBook();
                    break;
                case 10:
                    library.displayIssuedBooks();
                    break;
                case 11:
                    removeBook();
                    break;
                case 12:
                    exit = true;
                    System.out.println("\nThank you for using Library Management System!");
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
  
    private void registerStudent() {
        System.out.println("\n📝 STUDENT REGISTRATION");
        System.out.println("========================================");
        
      
        String studentId = getValidStudentId();
        if (studentId == null) return;
        
      
        if (library.studentExists(studentId)) {
            System.out.println("❌ Student already registered!");
            return;
        }
        
      
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        if (!Validator.isNotEmpty(name)) {
            System.out.println("❌ Name cannot be empty!");
            return;
        }
        
   
        String department = Validator.getDepartmentFromId(studentId);
     
        String mobile = getValidMobileNumber();
        if (mobile == null) return;
        
        // Register student
        Student student = new Student(studentId, name, department, mobile);
        library.registerStudent(student);
    }
  
    private void addBook() {
        System.out.println("\n📚 ADD NEW BOOK");
        System.out.println("========================================");
        
    
        String bookId = getValidBookId();
        if (bookId == null) return;
        
  
        if (library.bookExists(bookId)) {
            System.out.println("❌ Book ID already exists!");
            return;
        }
        
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine().trim();
        if (!Validator.isNotEmpty(title)) {
            System.out.println("❌ Title cannot be empty!");
            return;
        }
        
        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine().trim();
        if (!Validator.isNotEmpty(author)) {
            System.out.println("❌ Author cannot be empty!");
            return;
        }
        
        String department = Validator.getDepartmentFromId(bookId);
        
        System.out.print("Enter Book Price (₹): ");
        double price = getDoubleInput();
        if (price <= 0) {
            System.out.println("❌ Price must be greater than 0!");
            return;
        }
        
        System.out.print("Enter Number of Copies: ");
        int copies = getIntInput();
        if (copies <= 0) {
            System.out.println("❌ Copies must be greater than 0!");
            return;
        }
        
   
        Book book = new Book(bookId, title, author, department, price, copies);
        library.addBook(book);
    }
  
    private void searchBook() {
        System.out.println("\n🔍 SEARCH BOOKS");
        System.out.println("========================================");
        System.out.println("1. Search by Book ID");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");
        System.out.print("Enter your choice: ");
        
        int choice = getIntInput();
        String searchValue = "";
        
        switch (choice) {
            case 1:
                System.out.print("Enter Book ID: ");
                searchValue = scanner.nextLine().trim().toUpperCase();
                if (!Validator.isValidBookId(searchValue)) {
                    System.out.println("❌ Invalid Book ID format!");
                    return;
                }
                break;
            case 2:
                System.out.print("Enter Book Title (or part): ");
                searchValue = scanner.nextLine().trim();
                break;
            case 3:
                System.out.print("Enter Author Name (or part): ");
                searchValue = scanner.nextLine().trim();
                break;
            default:
                System.out.println("❌ Invalid choice!");
                return;
        }
        
        String searchType = "";
        switch (choice) {
            case 1: searchType = "id"; break;
            case 2: searchType = "title"; break;
            case 3: searchType = "author"; break;
        }
        
        List<Book> results = library.searchBooks(searchType, searchValue);
        
        if (results.isEmpty()) {
            System.out.println("❌ No books found!");
            return;
        }
        
        System.out.println("\n📚 SEARCH RESULTS");
        System.out.println("========================================================");
        System.out.printf("| %-8s | %-30s | %-20s | %-6s | %-9s | %-6s |\n",
            "Book ID", "Title", "Author", "Dept", "Price", "Available");
        System.out.println("========================================================");
        
        for (Book book : results) {
            System.out.printf("| %-8s | %-30s | %-20s | %-6s | ₹%-8.2f | %-3d/%d |\n",
                book.getBookId(),
                book.getTitle().length() > 30 ? book.getTitle().substring(0, 27) + "..." : book.getTitle(),
                book.getAuthor().length() > 20 ? book.getAuthor().substring(0, 17) + "..." : book.getAuthor(),
                book.getDepartment(),
                book.getPrice(),
                book.getAvailableCopies(),
                book.getTotalCopies()
            );
        }
        System.out.println("========================================================");
    }
  
    private void searchDepartmentBooks() {
        System.out.println("\n📚 SEARCH BOOKS BY DEPARTMENT");
        System.out.println("========================================");
        System.out.print("Enter Department (CSE/ECE/EEE/MEC/CIV/IT/AIM): ");
        String department = scanner.nextLine().trim().toUpperCase();
        
        if (!Validator.isValidDepartment(department)) {
            System.out.println("❌ Invalid department!");
            return;
        }
        
        List<Book> results = library.getBooksByDepartment(department);
        
        if (results.isEmpty()) {
            System.out.println("❌ No books found in " + department + " department!");
            return;
        }
        
        System.out.println("\n📚 BOOKS IN " + department + " DEPARTMENT");
        System.out.println("========================================================");
        System.out.printf("| %-8s | %-30s | %-20s | %-9s | %-6s |\n",
            "Book ID", "Title", "Author", "Price", "Available");
        System.out.println("========================================================");
        
        for (Book book : results) {
            System.out.printf("| %-8s | %-30s | %-20s | ₹%-8.2f | %-3d/%d |\n",
                book.getBookId(),
                book.getTitle().length() > 30 ? book.getTitle().substring(0, 27) + "..." : book.getTitle(),
                book.getAuthor().length() > 20 ? book.getAuthor().substring(0, 17) + "..." : book.getAuthor(),
                book.getPrice(),
                book.getAvailableCopies(),
                book.getTotalCopies()
            );
        }
        System.out.println("========================================================");
    }
    
   
    private void issueBook() {
        System.out.println("\n📚 ISSUE BOOK");
        System.out.println("========================================");
        
    
        library.displayAllBooks();
        
     
        String studentId = getValidStudentId();
        if (studentId == null) return;
        
    
        if (!library.studentExists(studentId)) {
            System.out.println("❌ Student not registered!");
            return;
        }
        
 
        String bookId = getValidBookId();
        if (bookId == null) return;
        
 
        if (!library.bookExists(bookId)) {
            System.out.println("❌ Book not found!");
            return;
        }
        
 
        library.issueBook(studentId, bookId);
    }

    private void returnBook() {
        System.out.println("\n📚 RETURN BOOK");
        System.out.println("========================================");
        
     
        String studentId = getValidStudentId();
        if (studentId == null) return;
        
        String bookId = getValidBookId();
        if (bookId == null) return;
        
        library.returnBook(studentId, bookId);
    }
 
    private void lostBook() {
        System.out.println("\n⚠️ LOST BOOK");
        System.out.println("========================================");
        
        String studentId = getValidStudentId();
        if (studentId == null) return;
        
        String bookId = getValidBookId();
        if (bookId == null) return;
        
        library.lostBook(studentId, bookId);
    }
  
    private void removeBook() {
        System.out.println("\n🗑️ REMOVE BOOK");
        System.out.println("========================================");
        
        String bookId = getValidBookId();
        if (bookId == null) return;
        
        library.removeBook(bookId);
    }
 
    private String getValidStudentId() {
        System.out.print("Enter Student ID (e.g., CSE001): ");
        String studentId = scanner.nextLine().trim().toUpperCase();
        
        if (!Validator.isValidStudentId(studentId)) {
            System.out.println("❌ Invalid Student ID!");
            System.out.println("Valid formats: CSE001-CSE999, ECE001-ECE999, etc.");
            return null;
        }
        
        return studentId;
    }
  
    private String getValidBookId() {
        System.out.print("Enter Book ID (e.g., CSE101): ");
        String bookId = scanner.nextLine().trim().toUpperCase();
        
        if (!Validator.isValidBookId(bookId)) {
            System.out.println("❌ Invalid Book ID!");
            System.out.println("Valid formats: CSE101, ECE101, etc.");
            return null;
        }
        
        return bookId;
    }
  
    private String getValidMobileNumber() {
        System.out.print("Enter Mobile Number (10 digits, starts with 6/7/8/9): ");
        String mobile = scanner.nextLine().trim();
        
        if (!Validator.isValidMobileNumber(mobile)) {
            System.out.println("❌ Invalid Mobile Number!");
            System.out.println("Must be 10 digits starting with 6,7,8,9");
            return null;
        }
        
        return Validator.formatMobileNumber(mobile);
    }
 
    private int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
  
    private double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
  
    private void printHeader() {
        System.out.println("\n=============================");
        System.out.println("  LIBRARY MANAGEMENT SYSTEM");
        System.out.println("  " + library.getLibraryName());
        System.out.println("=============================");
    }
}