package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public interface ItemDAO extends SuperDAO<Item, String> {
    boolean ifItemExist(String itemCode) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
