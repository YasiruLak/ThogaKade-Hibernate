package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item item) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(Item item) throws Exception {
        return false;
    }

    @Override
    public Item search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean ifItemExist(String itemCode) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
