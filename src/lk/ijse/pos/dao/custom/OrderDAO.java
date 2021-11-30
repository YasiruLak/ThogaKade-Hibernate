package lk.ijse.pos.dao.custom;

import com.sun.xml.bind.v2.model.core.ID;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Orders;
import org.hibernate.criterion.Order;

import javax.persistence.Id;
import javax.swing.text.html.parser.Entity;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public interface OrderDAO extends SuperDAO<Orders, String> {
    String generateOrderId() throws Exception;
}
