package lk.ijse.layeredarchitecture.dao.custom;

import lk.ijse.layeredarchitecture.dao.CrudDAO;
import lk.ijse.layeredarchitecture.dto.OrderDetailDTO;
import lk.ijse.layeredarchitecture.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetail> {
    boolean saveOrderDetails(OrderDetailDTO entity, Object orderId) throws SQLException, ClassNotFoundException;
}