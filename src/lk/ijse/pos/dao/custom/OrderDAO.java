package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Orders;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public interface OrderDAO extends SuperDAO<Orders, String> {
    String generateOrderId() throws Exception;
}
