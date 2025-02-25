package lk.ijse.layeredarchitecture.dao.custom.impl;

import lk.ijse.layeredarchitecture.dao.SqlUtil;
import lk.ijse.layeredarchitecture.dao.custom.ItemDAO;
import lk.ijse.layeredarchitecture.entity.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<Item> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Item");

        ArrayList<Item> itemArrayList = new ArrayList<>();

        while (rst.next()) {
            Item entity = new Item(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
            itemArrayList.add(entity);
        }
        return itemArrayList;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)", entity.getCode(), entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand(), entity.getCode());
    }

    @Override
    public void delete(String code) throws SQLException, ClassNotFoundException {
        SqlUtil.execute("DELETE FROM Item WHERE code=?", code);
    }

    @Override
    public boolean exists(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT code FROM Item WHERE code=?", code);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ArrayList<String> loadAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Item");

        ArrayList<String> itemCodesArrayList = new ArrayList<>();

        while (rst.next()) {
            itemCodesArrayList.add(rst.getString("code"));
        }
        return itemCodesArrayList;
    }

    @Override
    public Item searchById(String Id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Item WHERE code=?", Id);
        if (rst.next()) {
            return new Item(
                    Id,
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
        }
        return null;
    }
}
