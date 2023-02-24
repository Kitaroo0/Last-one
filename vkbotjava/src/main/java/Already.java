import java.sql.*;
public class Already extends Authorization{
    private String loginOrId = Authorization.getLogiin();
    public String getLoginOrId() {
        return loginOrId;
    }
    public void setLoginOrId(String loginOrId) {
        this.loginOrId = loginOrId;
    }
    private boolean checkLoginOrId(){
        if(loginOrId.length() == 12){
            for(int i = 0; i < 12; i++){
                if (!Character.isDigit(loginOrId.charAt(i))){
                    return true;
                }
            }
        }else{
            return true;
        }
        return false;
    }
    protected boolean AlreadyLog(String login) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaoop", "postgres", "laptopwithvirus");
            Statement statement = con.createStatement();
            ResultSet checkLogin = statement.executeQuery("SELECT * FROM datatable WHERE log = '" + login + "';");
            if(checkLogin.next()){
                return false;
            }else{
                con.close();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in connecting to postgres server");
            throw new RuntimeException(e);
        }
    }
    protected boolean AlreadyIIN(String login) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaoop", "postgres", "laptopwithvirus");
            Statement statement = con.createStatement();
            ResultSet checkLogin = statement.executeQuery("SELECT * FROM datatable WHERE iin = '" + login + "';");
            if(checkLogin.next()){
                return false;
            }else{
                con.close();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in connecting to postgres server");
            throw new RuntimeException(e);
        }
    }

    protected boolean AlreadyEmail(String email) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaoop", "postgres", "laptopwithvirus");
            Statement statement = con.createStatement();
            ResultSet checkLogin = statement.executeQuery("SELECT * FROM datatable WHERE email = '" + email + "';");
            if(checkLogin.next()){
                return false;
            }else{
                con.close();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in connecting to postgres server");
            throw new RuntimeException(e);
        }
    }

    protected boolean doesUserExist(String iin, String login, String password){
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaoop", "postgres", "laptopwithvirus");
            Statement statement = con.createStatement();
            if(checkLoginOrId()){
                ResultSet checkExistence = statement.executeQuery("SELECT * FROM datatable WHERE log = '" +
                        login + "' AND password = '" + PasswordHashing.hashPassword(password) + "';");
                if(checkExistence.next()){
                    return true;
                }else{
                    con.close();
                    return false;
                }}
            else{
                ResultSet checkExistence = statement.executeQuery("SELECT * FROM datatable WHERE iin = '" +
                        iin + "' AND password = '" + PasswordHashing.hashPassword(password) + "';");
                if(checkExistence.next()){
                    return true;
                }else{
                    con.close();
                    return false;
                    }
                }
        } catch (SQLException e) {
            System.out.println("Error in connecting to postgres server");
            throw new RuntimeException(e);
        }
    }
    public boolean Authorization(){
        return doesUserExist(loginOrId, loginOrId, getPassword());
    }
}
