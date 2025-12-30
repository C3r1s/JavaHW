package com.demo.dao;

import com.demo.model.User;

import java.sql.*;
import java.util.*;

public class UserDAO {
    private static final String URL  = "jdbc:postgresql://localhost:5432/demo?user=postgres&password=postgres";

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT id,login,password,email FROM users ORDER BY id";
        try (Connection c = getConn(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                list.add(u);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static void insert(User u) {
        String sql = "INSERT INTO users(login,password,email) VALUES (?,?,?)";
        try (Connection c = getConn(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void update(User u) {
        String sql = "UPDATE users SET login=?, password=?, email=? WHERE id=?";
        try (Connection c = getConn(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setInt(4, u.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection c = getConn(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static User findById(int id) {
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection c = getConn(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                return u;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}