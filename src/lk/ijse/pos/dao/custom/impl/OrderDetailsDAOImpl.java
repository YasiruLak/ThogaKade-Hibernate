package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.entity.OrderDetails;

import java.util.ArrayList;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(OrderDetails orderDetails) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public OrderDetails search(String s) throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }
}
