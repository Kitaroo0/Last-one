package entities;

public class User {
    private String login;
    private String email;
    private String password;
    private String IIN;

    public User(String login, String email, String password, String IIN){
        setEmail(email);
        setIIN(IIN);
        setLogin(login);
        setPassword(password);
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIIN() {
        return IIN;
    }

    public void setIIN(String IIN) {
        this.IIN = IIN;
    }
}