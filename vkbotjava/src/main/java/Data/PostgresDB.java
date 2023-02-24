package Data;

import Data.interfaces.IDB;

import java.sql.*;

public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/javaoop";
        String user_name = "postgres";
        String password = "laptopwithvirus";
        try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(connectionUrl,user_name ,  password);

            return  con;
        }catch (Exception e){
            e.getMessage();
            return null;
        }
    }
}
