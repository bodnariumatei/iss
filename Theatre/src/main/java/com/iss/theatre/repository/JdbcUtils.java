package com.iss.theatre.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtils {
    private Properties jdbcProps;

    public JdbcUtils(Properties props){
        jdbcProps=props;
    }

    private Connection instance=null;

    private Connection getNewConnection(){
        String driver=jdbcProps.getProperty("sm.jdbc.driver");
        String url=jdbcProps.getProperty("sm.jdbc.url");
        String user=jdbcProps.getProperty("sm.jdbc.user");
        String pass=jdbcProps.getProperty("sm.jdbc.pass");

        Connection con=null;
        try {
            Class.forName(driver);
            if (user!=null && pass!=null)
                con= DriverManager.getConnection(url,user,pass);
            else
                con=DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error getting connection "+e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver "+e);
        }
        return con;
    }

    public Connection getConnection(){
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return instance;
    }
}
