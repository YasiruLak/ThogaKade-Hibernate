package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public interface CustomerDAO extends SuperDAO<Customer, String> {
    boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
