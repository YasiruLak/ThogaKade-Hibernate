package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Orders;

import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public interface OrderDAO extends SuperDAO<Orders, String> {
    boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException;
    String generateOrderId() throws Exception;
}
