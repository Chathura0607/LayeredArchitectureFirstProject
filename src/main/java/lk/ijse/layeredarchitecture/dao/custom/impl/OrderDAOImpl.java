package lk.ijse.layeredarchitecture.dao.custom.impl;

import lk.ijse.layeredarchitecture.dao.SqlUtil;
import lk.ijse.layeredarchitecture.dao.custom.OrderDAO;
import lk.ijse.layeredarchitecture.entity.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    private static  Connection connection;

    @Override
    public ArrayList<Order> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Orders (oid, date, customerID) VALUES (?,?,?)", entity.getOrderId(), entity.getOrderDate(), entity.getCustomerId());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT oid FROM `Orders` WHERE oid=?", id);
        return resultSet.next();
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

        if (rst.next()) {
            int lastId = Integer.parseInt(rst.getString("oid").replace("OID-", ""));
            return String.format("OID-%03d", lastId + 1);
        } else {
            return "OID-001";
        }
    }

    @Override
    public ArrayList<String> loadAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Order searchById(String Id) throws SQLException, ClassNotFoundException {
        //Connection connection = DBConnection.getDbConnection().getConnection();
        return SqlUtil.execute("SELECT oid FROM Orders WHERE oid=?", Id);
    }
}
