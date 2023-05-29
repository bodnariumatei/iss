package com.iss.theatre.repository;

import com.iss.theatre.model.Show;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ShowsRepository {
    private JdbcUtils dbUtils;

    public ShowsRepository(Properties props) {
        this.dbUtils = new JdbcUtils(props);
    }

    public ShowsRepository() {
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

    public Iterable<Show> getAll() {
        Connection con = dbUtils.getConnection();
        List<Show> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from shows")) {
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    int duration = result.getInt("duration");
                    Show show = new Show(id, name, duration);
                    users.add(show);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return users;
    }

    public Show getOne(int id){
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from shows where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                int duration = result.getInt("duration");
                return new Show(id, name, duration);
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }

    public Show getOneByName(String name){
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from shows where name = ?")) {
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                int duration = result.getInt("duration");
                return new Show(id, name, duration);
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }

    public Show store(Show show){
        Connection con = dbUtils.getConnection();
        try(PreparedStatement insertStatement = con.prepareStatement("insert into shows(name, duration) values (?, ?)")){
            insertStatement.setString(1, show.getName());
            insertStatement.setInt(2, show.getDuration());
            insertStatement.executeUpdate();

            return getOneByName(show.getName());
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return null;
    }

    public Show delete(int id){
        Connection con = dbUtils.getConnection();
        Show show = getOne(id);
        try(PreparedStatement deleteStatement = con.prepareStatement("delete from shows where id = ?")){
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
            return show;
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return null;
    }
}
