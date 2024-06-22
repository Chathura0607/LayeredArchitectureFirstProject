package lk.ijse.layeredarchitecture.dao.custom.impl;

import lk.ijse.layeredarchitecture.dao.SqlUtil;
import lk.ijse.layeredarchitecture.dao.custom.CustomerDAO;
import lk.ijse.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public ArrayList<Customer> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customerArrayList = new ArrayList<>();

        while (rst.next()) {
            Customer entity = new Customer(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")
            );
            customerArrayList.add(entity);
        }
        return customerArrayList;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", entity.getId(), entity.getName(), entity.getAddress());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?", entity.getName(), entity.getAddress(), entity.getId());
    }

    @Override
    public boolean exists(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return resultSet.next();
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SqlUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String result;
        ResultSet rst = SqlUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            result = String.format("C00-%03d", newCustomerId);
        } else {
            result = "C00-001";
        }
        return result;
    }
    
    @Override
    public ArrayList<String> loadAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Customer");

        ArrayList<String> customerIdArrayList = new ArrayList<>();

        while (rst.next()) {
            customerIdArrayList.add(rst.getString("id"));
        }
        return customerIdArrayList;
    }

    @Override
    public Customer searchById(String Id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Customer WHERE id=?", Id);
        if (rst.next()) {
            return new Customer(
                    Id,
                    rst.getString("name"),
                    rst.getString("address")
            );
        }
        return null;
    }
}
