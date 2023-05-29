package com.iss.theatre.repository;

import com.iss.theatre.model.User;
import com.iss.theatre.model.UserType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepository {
    private JdbcUtils dbUtils;

    public UserRepository(Properties props) {
        this.dbUtils = new JdbcUtils(props);
    }

    public UserRepository(){
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("db.properties"));
            System.out.println("Database properties set. ");
            properties.list(System.out);
            this.dbUtils = new JdbcUtils(properties);
        } catch (IOException e) {
            System.err.println("Cannot find db.properties "+e);
        }
    }

    public User getOneByUsernameAndPassword(String username, String password) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from users where username=? and password=?")){
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) {
                int id = result.getInt("id");
                String oUsername = result.getString("username");
                String oPassword = result.getString("password");
                UserType type = UserType.valueOf(result.getString("type"));
                return new User(id, oUsername, oPassword, type);
            }
        }catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }

    public Iterable<User> getAll() {
        Connection con = dbUtils.getConnection();
        List<User> users = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from users")){
            try(ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()){
                    int id = result.getInt("id");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    UserType type = UserType.valueOf(result.getString("type"));
                    User admin = new User(id, username, password, type);
                    users.add(admin);
                }
            }
        }catch (SQLException ex){
            System.err.println("Error DB "+ex);
        }
        return users;
    }
}
