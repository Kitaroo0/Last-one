package repositories;

import Data.interfaces.IDB;
import entities.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db ;
    public  UserRepository (IDB db){
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql ="INSERT INTO datatable (log, iin, email, password ) VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getLogin());
            st.setString(2, user.getIIN());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());

            st.execute();
            return  true;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public User getUser(String login) {

        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT login, iin, email, password FROM user WHERE login=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, login);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                User user = new User(rs.getString("login"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("iin"));

                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
