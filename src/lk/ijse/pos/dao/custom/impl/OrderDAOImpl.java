package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Orders;
import org.hibernate.criterion.Order;

import java.util.ArrayList;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean add(Orders orders) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(Orders orders) throws Exception {
        return false;
    }

    @Override
    public Orders search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Orders> getAll() throws Exception {
        return null;
    }

    @Override
    public String generateOrderId() throws Exception {
        return null;
    }
}
