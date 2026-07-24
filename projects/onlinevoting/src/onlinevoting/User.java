package onlinevoting;
public class User {
    private String username;
    private String useridno;
    private String password;
    public User(String username,String useridno, String password) {
        this.username = username;
        this.useridno = useridno;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getUseridno() {
        return useridno;
    }
    public String getPassword() {
        return password;
    }
}