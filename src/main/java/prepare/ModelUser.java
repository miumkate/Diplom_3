package prepare;

public class ModelUser {

    private String email;
    private String password;
    private String name;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public ModelUser(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.name = username;
    }
}
