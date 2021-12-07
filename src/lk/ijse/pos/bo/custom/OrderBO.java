package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public interface OrderBO extends SuperBO {
    boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;

    String generateNewOrderId() throws Exception;

    ArrayList<CustomerDTO> getAllCustomers() throws Exception;

    ArrayList<ItemDTO> getAllItems() throws Exception;


    ItemDTO searchItem(String code) throws Exception;

    boolean ifItemExist(String code) throws SQLException, ClassNotFoundException;

    boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String s) throws Exception;
}
