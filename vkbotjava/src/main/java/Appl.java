import controrllers.UserController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Appl extends Already{
    private final UserController controller;

    Already already = new Already();
    Authorization auth = new Authorization();
    SendMail send;
    private final Scanner sc = new Scanner(System.in);

    public Appl(UserController controller) {
        this.controller = controller;
    }
    public void startreg(){
        System.out.println("Register your account.\nThink up login: ");
        auth.setLogin(sc.nextLine());
        while(!AlreadyLog(auth.getLogin()))
        {
            System.out.println("This login is already registered.\nTry again: ");
            auth.setLogin(sc.nextLine());
        };
        System.out.print("Enter your email: ");
        send.setTo(sc.nextLine());
        while(!AlreadyEmail(send.getTo()))
        {
            System.out.println("This email is already registered.\nTry again: ");
            send.setTo(sc.nextLine());
        };
        auth.setEmail(send.getTo());
        System.out.print("Enter your IIN: ");
        auth.setIIN(sc.nextLine());
        while(!AlreadyIIN(auth.getIIN()) || 12 != auth.getIIN().length())
        {
            if (12 != auth.getIIN().length())
            {
                while( 12 != auth.getIIN().length())
                {
                    System.out.print("The IIN must consist of 12 digits.\nTry again:");
                    auth.setIIN(sc.nextLine());
                }
            }
            if (!AlreadyIIN(auth.getIIN()))
            {System.out.println("This IIN is already registered.\nTry again: ");
                auth.setIIN(sc.nextLine());}
        };
        System.out.print("Think up password: ");
        auth.setPassword(sc.nextLine());
        while (!Reset.isValidPassword(auth.getPassword()))
        {
            System.out.println("Invalid password. Password must be at least 8 characters long, contain at least one digit, lowercase letter, uppercase letter.\nPlease try again:");
            auth.setPassword(sc.nextLine());
        }
        CodeGen num = new CodeGen();
        send = new SendMail("Confirmation code: " + num.getCode());
        System.out.print("To confirm the mail, enter the code that was sent to you.\nConfirmation code: ");
        while (!num.checkCode(sc.next())) {
            System.out.print("Wrong code! Try again: ");
        }
        controller.createUser(auth.getLogin(),auth.getEmail(),PasswordHashing.hashPassword(auth.getPassword()),auth.getIIN());
        System.out.print("You have successfully registered.\n");
    }
    public void startlog(){
        int h = 4, q;
        System.out.print("Enter login or IIN: ");
        auth.setLogin(sc.next());
        already.setLoginOrId(auth.getLogin());
        System.out.print("Enter password: ");
        Authorization.setPassword(sc.next());

        while (!already.Authorization()) {
            System.out.print("Incorrect login/IIN or password!\n-Enter the 1 if you want to reset your password.\n-Enter the 2 if you want to try again.\n");
            q = sc.nextInt();
            if (q == 1 || h == 1)
            {
                System.out.print("You need to reset your password.\nEnter your email: ");
                send.setTo(sc.next());
                auth.setEmail(send.getTo());
                CodeGen2 sure = new CodeGen2();
                new SendMail("Verification  code: " + sure.getCode());
                System.out.print("To make sure, enter the code that we sent to your email: ");
                while (!sure.checkCode(sc.next())) {
                    System.out.print("Wrong code! Try again: ");
                }
                System.out.println("Password successfully reset!");
                PassGen code = new PassGen();
                new SendMail("Your new password: " + code.getCode());
                auth.setPassword(code.getCode());
                //Update database password
                try{
                    Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();

                    try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaoop", "postgres", "laptopwithvirus")){

                        Statement statement = conn.createStatement();

                        int rows = statement.executeUpdate("UPDATE datatable SET password = '" + PasswordHashing.hashPassword(auth.getPassword()) + "' WHERE email = '" + auth.getEmail() + "';");
                    }
                }
                catch(Exception ex){
                    System.out.println("Connection failed...");
                    System.out.println(ex);
                }
                System.out.print("Enter your new password from email: ");
                while (!code.checkCode(sc.next()))
                {
                    System.out.print("Wrong password! Try again: ");
                }
                break;
            }
            if (q == 2)
            {
                h--;
                System.out.print("You have only " + h + " chance\n");
                System.out.print("Enter login or IIN: ");
                already.setLoginOrId(sc.next());
                System.out.print("Enter password: ");
                Authorization.setPassword(sc.next());
            }
            else {
                System.out.print("I do not know this number\n");
            }
        }
        System.out.print("You have successfully logged in!\n");
    }
    public String getLog() {
        return auth.getLogin();
    }
}
