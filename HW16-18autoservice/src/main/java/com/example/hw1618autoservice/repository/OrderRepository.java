package com.example.hw1618autoservice.repository;

import com.example.hw1618autoservice.db.DatabaseConnection;
import com.example.hw1618autoservice.model.ServiceOrder;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static final OrderRepository INSTANCE = new OrderRepository();

    private OrderRepository() {}

    public static OrderRepository getInstance() {
        return INSTANCE;
    }

    public List<ServiceOrder> findAll() {
        List<ServiceOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM service_orders ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                orders.add(new ServiceOrder(
                        rs.getLong("id"),
                        rs.getString("customer_name"),
                        rs.getString("car_model"),
                        rs.getString("service_description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public ServiceOrder findById(long id) {
        String sql = "SELECT * FROM service_orders WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ServiceOrder(
                            rs.getLong("id"),
                            rs.getString("customer_name"),
                            rs.getString("car_model"),
                            rs.getString("service_description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(ServiceOrder order) {
        String sql = "INSERT INTO service_orders (customer_name, car_model, service_description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order.getCustomerName());
            pstmt.setString(2, order.getCarModel());
            pstmt.setString(3, order.getServiceDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ServiceOrder order) {
        String sql = "UPDATE service_orders SET customer_name = ?, car_model = ?, service_description = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order.getCustomerName());
            pstmt.setString(2, order.getCarModel());
            pstmt.setString(3, order.getServiceDescription());
            pstmt.setLong(4, order.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM service_orders WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}