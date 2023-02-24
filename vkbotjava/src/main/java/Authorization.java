public class Authorization{
    private static String login;
    private static String password;
    private static String IIN;
    private static String email;
    private static String logiin;

    public static String getLogiin() {
        return logiin;
    }

    public static void setLogiin(String logiin) {
        Authorization.logiin = logiin;
    }

    public void setLogin(String login) {
        Authorization.login = login;
    }

    public static void setPassword(String password) {
        Authorization.password = password;
    }

    public void setIIN(String IIN) {
        Authorization.IIN = IIN;
    }

    public void setEmail(String email) {
        Authorization.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public static String getIIN() {
        return IIN;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public boolean checkLogin(String input) {
        return input.equals(login);
    }

}