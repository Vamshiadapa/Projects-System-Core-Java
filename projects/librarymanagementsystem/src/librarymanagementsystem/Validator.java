package librarymanagementsystem;

import java.util.regex.Pattern;


public class Validator {
    
    private static final String STUDENT_ID_PATTERN = 
        "^(CSE|ECE|EEE|MEC|CIV|IT|AIM)[0-9]{3}$";
    private static final String MOBILE_PATTERN = "^[6-9][0-9]{9}$";
    private static final String BOOK_ID_PATTERN = 
        "^(CSE|ECE|EEE|MEC|CIV|IT|AIM)[0-9]{3}$";
   
    public static boolean isValidStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        
        String id = studentId.trim().toUpperCase();
        
       
        if (!Pattern.matches(STUDENT_ID_PATTERN, id)) {
            return false;
        }
        
        String dept = studentId.substring(0, 3);

        switch (dept) {
            case "CSE":
            case "ECE":
            case "EEE":
            case "MEC":
            case "CIV":
            case "IT":
            case "AIM":
                break;
            default:
                return false;
        }
        int number = Integer.parseInt(id.substring(3));
      
        return number >= 1 && number <= 999;
    }
    
 
    public static String getDepartmentFromId(String studentId) {
        if (!isValidStudentId(studentId)) {
            return null;
        }
        return studentId.substring(0, 3).toUpperCase();
    }
    
   
    public static boolean isValidMobileNumber(String mobile) {
        if (mobile == null || mobile.trim().isEmpty()) {
            return false;
        }
        
        
        String cleaned = mobile.replaceAll("[\\s\\-()]", "");
        return Pattern.matches(MOBILE_PATTERN, cleaned);
    }
    
    
    public static boolean isValidBookId(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            return false;
        }
        
        String id = bookId.trim().toUpperCase();
        
        if (!Pattern.matches(BOOK_ID_PATTERN, id)) {
            return false;
        }
        
        int number = Integer.parseInt(id.substring(3));
        return number >= 1 && number <= 999;
    }
    
    
    public static boolean isValidDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            return false;
        }
        
        String dept = department.trim().toUpperCase();
        String[] validDepts = {"CSE", "ECE", "EEE", "MEC", "CIV", "IT", "AIM"};
        
        for (String valid : validDepts) {
            if (valid.equals(dept)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
    
    public static boolean isPositive(double value) {
        return value > 0;
    }
   
    public static boolean isPositive(int value) {
        return value > 0;
    }
   
    public static String formatStudentId(String studentId) {
        if (studentId == null) {
            return null;
        }
        return studentId.trim().toUpperCase();
    }
    
    
    public static String formatMobileNumber(String mobile) {
        if (mobile == null) {
            return null;
        }
        return mobile.replaceAll("[\\s\\-()]", "");
    }
}