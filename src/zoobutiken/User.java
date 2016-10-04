package zoobutiken;

public class User {

    protected String name;
    protected String username;
    protected String password;
    protected boolean isAdmin;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

    public String toString() {
        return name + "," + username + "," + password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
