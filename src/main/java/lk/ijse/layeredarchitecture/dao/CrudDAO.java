package lk.ijse.layeredarchitecture.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO {
    ArrayList<T> loadAll() throws SQLException, ClassNotFoundException;

    boolean save(T t) throws SQLException, ClassNotFoundException;
    
    boolean update(T t) throws SQLException, ClassNotFoundException;

    boolean exists(String id) throws SQLException, ClassNotFoundException;

    void delete(String id) throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;

    T searchById(String Id) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadAllIds() throws SQLException, ClassNotFoundException;
}
