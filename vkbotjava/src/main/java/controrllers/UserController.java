package controrllers;

import entities.User;
import repositories.interfaces.IUserRepository;

public class UserController {
    private final IUserRepository repo;

    public  UserController (IUserRepository userRepository){
        this.repo = userRepository;
    }
    public String createUser (String login , String  email , String password,  String IIN ){

        User user  =  new User(login , email , password , IIN);
        return  repo.createUser(user) ? "User was created!" : "User creation was failed!";
    }
    public String getUser(String login) {
        User user = repo.getUser(login);

        return (user == null ? "User was not found!" : user.toString());
    }
}