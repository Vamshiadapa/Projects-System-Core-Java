package librarymanagementsystem;


public class Main {
    
    public static void main(String[] args) {
        System.out.println("Initializing Library Management System...");
        
     
        Library library = new Library("Central Library");
        
     
        initializeSampleData(library);
        
      
        Menu menu = new Menu(library);
        menu.displayMainMenu();
    }
    
    private static void initializeSampleData(Library library) {
        System.out.println("Loading sample data...");
        
        // CSE Books
        library.addBook(new Book("CSE101", "Java Programming", "Herbert Schildt", "CSE", 650, 5));
        library.addBook(new Book("CSE102", "Data Structures", "Mark Allen Weiss", "CSE", 750, 4));
        library.addBook(new Book("CSE103", "Database Systems", "Abraham Silberschatz", "CSE", 800, 3));
        library.addBook(new Book("CSE104", "Operating Systems", "Andrew Tanenbaum", "CSE", 700, 4));
        library.addBook(new Book("CSE105", "Computer Networks", "James Kurose", "CSE", 680, 3));
        library.addBook(new Book("CSE106", "Algorithms", "Thomas Cormen", "CSE", 850, 3));
        library.addBook(new Book("CSE107", "Software Engineering", "Ian Sommerville", "CSE", 600, 5));
        library.addBook(new Book("CSE108", "Python Programming", "John Zelle", "CSE", 550, 6));
        
        // ECE Books
        library.addBook(new Book("ECE101", "Digital Electronics", "Morris Mano", "ECE", 550, 4));
        library.addBook(new Book("ECE102", "Signal Processing", "Alan Oppenheim", "ECE", 680, 3));
        library.addBook(new Book("ECE103", "Microprocessors", "Barry B. Brey", "ECE", 600, 4));
        library.addBook(new Book("ECE104", "Communication Systems", "Simon Haykin", "ECE", 720, 3));
        library.addBook(new Book("ECE105", "Embedded Systems", "Raj Kamal", "ECE", 580, 4));
        library.addBook(new Book("ECE106", "VLSI Design", "Wayne Wolf", "ECE", 650, 3));
        
        // EEE Books
        library.addBook(new Book("EEE101", "Electrical Circuits", "William Hayt", "EEE", 580, 4));
        library.addBook(new Book("EEE102", "Power Systems", "John Grainger", "EEE", 720, 3));
        library.addBook(new Book("EEE103", "Control Systems", "Ogata Katsuhiko", "EEE", 650, 3));
        library.addBook(new Book("EEE104", "Electric Machines", "P.C. Sen", "EEE", 600, 4));
        library.addBook(new Book("EEE105", "Renewable Energy", "Godfrey Boyle", "EEE", 550, 3));
        
        // MEC Books
        library.addBook(new Book("MEC101", "Thermodynamics", "Yunus Cengel", "MEC", 700, 4));
        library.addBook(new Book("MEC102", "Fluid Mechanics", "Frank White", "MEC", 680, 3));
        library.addBook(new Book("MEC103", "Machine Design", "J.E. Shigley", "MEC", 750, 3));
        library.addBook(new Book("MEC104", "Manufacturing", "Mikell Groover", "MEC", 600, 4));
        library.addBook(new Book("MEC105", "Automobile Engineering", "R.B. Gupta", "MEC", 550, 3));
        
        // CIV Books
        library.addBook(new Book("CIV101", "Structural Analysis", "R.C. Hibbeler", "CIV", 650, 4));
        library.addBook(new Book("CIV102", "Geotechnical Engineering", "Braja Das", "CIV", 700, 3));
        library.addBook(new Book("CIV103", "Construction Management", "K.K. Chitkara", "CIV", 550, 4));
        library.addBook(new Book("CIV104", "Hydrology", "K. Subramanya", "CIV", 600, 3));
        
        // IT Books
        library.addBook(new Book("IT101", "Web Development", "Jon Duckett", "IT", 600, 5));
        library.addBook(new Book("IT102", "Cloud Computing", "Rajkumar Buyya", "IT", 680, 3));
        library.addBook(new Book("IT103", "Cybersecurity", "William Stallings", "IT", 720, 3));
        library.addBook(new Book("IT104", "IoT Systems", "Raj Kamal", "IT", 620, 4));
        
        // AIM Books
        library.addBook(new Book("AIM101", "Artificial Intelligence", "Stuart Russell", "AIM", 850, 3));
        library.addBook(new Book("AIM102", "Machine Learning", "Andreas Muller", "AIM", 800, 3));
        library.addBook(new Book("AIM103", "Deep Learning", "Ian Goodfellow", "AIM", 900, 2));
        library.addBook(new Book("AIM104", "NLP Systems", "Dan Jurafsky", "AIM", 750, 3));
        
        System.out.println("✅ Sample data loaded successfully!");
        System.out.println("   Books available: " + library.getBooks().size());
        System.out.println("\n   📚 Welcome to Central Library!");
        System.out.println("   🔍 Use the menu to explore features.\n");
    }
}