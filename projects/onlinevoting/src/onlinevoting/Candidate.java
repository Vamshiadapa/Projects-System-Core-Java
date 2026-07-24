package onlinevoting;
public class Candidate {
    private String name;
    private String department;
    public Candidate(String name, String department) {
        this.name = name;
        this.department = department;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    public String toString() {
        return name + " (" + department + ")";
    }
}