package repositories.interfaces;

import entities.User;

public interface IUserRepository {
        boolean createUser(User user);
        User getUser(String login);
}
