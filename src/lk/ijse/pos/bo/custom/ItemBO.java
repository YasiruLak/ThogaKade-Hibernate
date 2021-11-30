package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public interface ItemBO extends SuperBO {

    ArrayList<ItemDTO> getAllItems() throws Exception;

    boolean deleteItem(String code) throws Exception;

    boolean addItem(ItemDTO dto) throws Exception;

    boolean updateItem(ItemDTO dto) throws Exception;

    boolean ifItemExist(String code) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
