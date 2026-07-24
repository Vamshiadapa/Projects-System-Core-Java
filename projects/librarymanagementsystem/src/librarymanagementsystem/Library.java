package librarymanagementsystem;

import java.util.*;
import java.time.LocalDate;


public class Library {
    private String libraryName;
    private Map<String, Book> books;
    private Map<String, Student> students;
    private Map<String, IssuedBook> issuedBooks;
  
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new HashMap<>();
        this.students = new HashMap<>();
        this.issuedBooks = new HashMap<>();
    }

    public boolean addBook(Book book) {
        String bookId = book.getBookId();
        
       
        if (books.containsKey(bookId)) {
            System.out.println("❌ Book ID already exists!");
            return false;
        }
        
        books.put(bookId, book);
        System.out.println("✅ Book added successfully!");
        return true;
    }
 
    public boolean removeBook(String bookId) {
        String id = bookId.toUpperCase();
        
        if (!books.containsKey(id)) {
            System.out.println("❌ Book not found!");
            return false;
        }
        
        Book book = books.get(id);
        
        if (book.isIssued()) {
            System.out.println("❌ Book is currently issued and cannot be removed!");
            return false;
        }
        
        books.remove(id);
        System.out.println("✅ Book removed successfully!");
        return true;
    }

    public boolean registerStudent(Student student) {
        String studentId = student.getStudentId();
        
        if (students.containsKey(studentId)) {
            System.out.println("❌ Student already registered!");
            return false;
        }
        
        students.put(studentId, student);
        System.out.println("✅ Student registered successfully!");
        return true;
    }
    
    public boolean issueBook(String studentId, String bookId) {
        String sId = studentId.toUpperCase();
        String bId = bookId.toUpperCase();
        
        if (!students.containsKey(sId)) {
            System.out.println("❌ Student not registered!");
            return false;
        }
        
   
        if (!books.containsKey(bId)) {
            System.out.println("❌ Book not found!");
            return false;
        }
        
        Student student = students.get(sId);
        Book book = books.get(bId);
        
        
        if (!book.isAvailable()) {
            System.out.println("❌ Book is not available!");
            return false;
        }
     
        if (student.hasBookIssued(bId)) {
            System.out.println("❌ Student already has this book!");
            return false;
        }
        
       
        if (student.getIssuedBooksCount() >= 3) {
            System.out.println("❌ Student has already issued maximum 3 books!");
            return false;
        }
        
       
        book.issueCopy();
        IssuedBook issuedBook = new IssuedBook(book, student);
        student.addIssuedBook(issuedBook);
        issuedBooks.put(sId + bId, issuedBook);
        
        System.out.println("\n Book issued successfully!");
        System.out.println(" Issue Details:");
        System.out.println("  Student: " + student.getName() + " (" + sId + ")");
        System.out.println("  Book: " + book.getTitle() + " (" + bId + ")");
        System.out.println("  Issue Date: " + issuedBook.getIssueDate());
        System.out.println("  Due Date: " + issuedBook.getDueDate());
        System.out.println("  ⚠️ Return within 15 days to avoid fine");
        return true;
    }
  
    public boolean returnBook(String studentId, String bookId) {
        String sId = studentId.toUpperCase();
        String bId = bookId.toUpperCase();
        String key = sId + bId;
        
        if (!issuedBooks.containsKey(key)) {
            System.out.println("❌ Book not issued to this student!");
            return false;
        }
        
        IssuedBook issuedBook = issuedBooks.get(key);
        double fine = issuedBook.calculateFine();
      
        issuedBook.returnBook();
        Student student = students.get(sId);
        student.removeIssuedBook(bId);
        issuedBooks.remove(key);
        
        System.out.println("\n✅ Book returned successfully!");
        System.out.println("📚 Return Details:");
        System.out.println("  Student: " + student.getName() + " (" + sId + ")");
        System.out.println("  Book: " + issuedBook.getBook().getTitle());
        System.out.println("  Return Date: " + LocalDate.now());
        
        if (fine > 0) {
            System.out.println("  ⚠️ Book is overdue by " + 
                issuedBook.getDaysOverdue() + " days");
            System.out.println("  Fine: ₹" + String.format("%.2f", fine));
        } else {
            System.out.println("  ✅ No fine applicable");
        }
        
        return true;
    }
  
    public boolean lostBook(String studentId, String bookId) {
        String sId = studentId.toUpperCase();
        String bId = bookId.toUpperCase();
        String key = sId + bId;
        
        if (!issuedBooks.containsKey(key)) {
            System.out.println("❌ Book not issued to this student!");
            return false;
        }
        
        IssuedBook issuedBook = issuedBooks.get(key);
        Book book = issuedBook.getBook();
        double totalAmount = issuedBook.calculateLostBookAmount();
  
        Student student = students.get(sId);
        student.removeIssuedBook(bId);
        issuedBooks.remove(key);
     
        books.remove(bId);
        
        System.out.println("\n⚠️ Lost Book Report:");
        System.out.println("📚 Book Details:");
        System.out.println("  Book ID: " + bId);
        System.out.println("  Book Name: " + book.getTitle());
        System.out.println("  Book Price: ₹" + String.format("%.2f", book.getPrice()));
        System.out.println("  Processing Charge: ₹100.00");
        System.out.println("  Total Amount Due: ₹" + String.format("%.2f", totalAmount));
        System.out.println("\n👤 Student Details:");
        System.out.println("  Name: " + student.getName());
        System.out.println("  ID: " + sId);
        System.out.println("  Department: " + student.getDepartment());
        
        return true;
    }
    
    public List<Book> searchBooks(String searchType, String searchValue) {
        List<Book> results = new ArrayList<>();
        
        if (books.isEmpty()) {
            return results;
        }
        
        String value = searchValue.toLowerCase().trim();
        
        for (Book book : books.values()) {
            boolean matches = false;
            
            switch (searchType.toLowerCase()) {
                case "id":
                    matches = book.getBookId().equalsIgnoreCase(value);
                    break;
                case "title":
                    matches = book.getTitle().toLowerCase().contains(value);
                    break;
                case "author":
                    matches = book.getAuthor().toLowerCase().contains(value);
                    break;
                case "department":
                    matches = book.getDepartment().equalsIgnoreCase(value);
                    break;
                default:
                    return results;
            }
            
            if (matches) {
                results.add(book);
            }
        }
        
        return results;
    }
   
    public List<Book> getBooksByDepartment(String department) {
        List<Book> deptBooks = new ArrayList<>();
        
        for (Book book : books.values()) {
            if (book.getDepartment().equalsIgnoreCase(department)) {
                deptBooks.add(book);
            }
        }
        
        return deptBooks;
    }
    
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("\n📚 No books in the library!");
            return;
        }
        
        System.out.println("\n📚 ALL BOOKS IN LIBRARY");
      
        System.out.printf("| %-8s | %-30s | %-20s | %-6s | %-9s | %-6s |\n",
            "Book ID", "Title", "Author", "Dept", "Price", "Available");
      
        for (Book book : books.values()) {
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

        System.out.println("Total Books: " + books.size());
    }
   
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("\n👤 No students registered!");
            return;
        }
        
        System.out.println("\n👤 REGISTERED STUDENTS");
      
        System.out.printf("| %-10s | %-20s | %-6s | %-12s | %-6s |\n",
            "Student ID", "Name", "Dept", "Mobile", "Books");
 
        
        for (Student student : students.values()) {
            System.out.printf("| %-10s | %-20s | %-6s | %-12s | %-3d  |\n",
                student.getStudentId(),
                student.getName().length() > 20 ? student.getName().substring(0, 17) + "..." : student.getName(),
                student.getDepartment(),
                student.getMobileNumber(),
                student.getIssuedBooksCount()
            );
        }
      
        System.out.println("Total Students: " + students.size());
    }
    
 
    public void displayIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println("\n📚 No books currently issued!");
            return;
        }
        
        System.out.println("\n📚 CURRENTLY ISSUED BOOKS");

        System.out.printf("| %-10s | %-20s | %-20s | %-12s | %-12s | %-12s |\n",
            "Student ID", "Student Name", "Book Name", "Dept", "Issue Date", "Due Date");
       
        for (IssuedBook issued : issuedBooks.values()) {
            Student student = issued.getStudent();
            Book book = issued.getBook();
            
            String status = issued.isOverdue() ? "⚠️ OVERDUE" : "Active";
            
            System.out.printf("| %-10s | %-20s | %-20s | %-6s | %-12s | %-12s | %s\n",
                student.getStudentId(),
                student.getName().length() > 20 ? student.getName().substring(0, 17) + "..." : student.getName(),
                book.getTitle().length() > 20 ? book.getTitle().substring(0, 17) + "..." : book.getTitle(),
                book.getDepartment(),
                issued.getIssueDate().toString(),
                issued.getDueDate().toString(),
                status
            );
        }
      
        System.out.println("Total Issued: " + issuedBooks.size());
    }
    

    public Student getStudent(String studentId) {
        return students.get(studentId.toUpperCase());
    }
  
    public Book getBook(String bookId) {
        return books.get(bookId.toUpperCase());
    }
 
    public boolean studentExists(String studentId) {
        return students.containsKey(studentId.toUpperCase());
    }
    
    public boolean bookExists(String bookId) {
        return books.containsKey(bookId.toUpperCase());
    }
 
    public boolean isBookIssuedToStudent(String studentId, String bookId) {
        return issuedBooks.containsKey(studentId.toUpperCase() + bookId.toUpperCase());
    }
    
    public IssuedBook getIssuedBook(String studentId, String bookId) {
        return issuedBooks.get(studentId.toUpperCase() + bookId.toUpperCase());
    }
    
    public String getLibraryName() {
        return libraryName;
    }
    
    public Map<String, Book> getBooks() {
        return books;
    }
    
    public Map<String, Student> getStudents() {
        return students;
    }
    
    public Map<String, IssuedBook> getIssuedBooks() {
        return issuedBooks;
    }
}