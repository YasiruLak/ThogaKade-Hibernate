package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Orders;
import lk.ijse.pos.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
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
    public boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateOrderId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createSQLQuery("SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1");

        String id = (String) query.uniqueResult();

        transaction.commit();

        session.close();

        if (id != null) {
            int tempId = Integer.
                    parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "O-00" + tempId;
            } else if (tempId <= 99) {
                return "O-0" + tempId;
            } else {
                return "O-" + tempId;
            }
        } else {
            return "O-001";

        }
    }
}
