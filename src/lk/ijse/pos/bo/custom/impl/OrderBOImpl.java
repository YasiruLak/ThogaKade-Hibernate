package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public class OrderBOImpl implements OrderBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewOrderId() throws Exception {
        return orderDAO.generateOrderId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getId(), customer.getTitle(), customer.getName(),
                    customer.getAddress(), customer.getCity(), customer.getProvince(), customer.getPostalCode()));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws Exception {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item : all) {
            allItems.add(new ItemDTO(item.getItemCode(), item.getDescription(), item.getPackSize(), item.getUnitPrice(),
                    item.getQtyOnHand()));
        }
        return allItems;

    }

    @Override
    public ItemDTO searchItem(String code) throws Exception {
        Item item = itemDAO.search(code);
        return new ItemDTO(item.getItemCode(), item.getDescription(), item.getPackSize(), item.getUnitPrice(),
                item.getQtyOnHand());
    }

    @Override
    public boolean ifItemExist(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.ifItemExist(code);
    }

    @Override
    public boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.ifCustomerExist(id);
    }

    @Override
    public CustomerDTO searchCustomer(String s) throws Exception {
        Customer c = customerDAO.search(s);
        return new CustomerDTO(c.getId(), c.getTitle(), c.getName(), c.getAddress(),
                c.getCity(), c.getProvince(), c.getPostalCode());
    }
}
