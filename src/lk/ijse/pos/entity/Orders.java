package lk.ijse.pos.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public class Orders implements SuperEntity {
    private String orderId;
    private String customerId;
    private Date Date;
    private Time Time;
    private double total;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> orderDetails;

    public Orders() {
    }

    public Orders(String orderId, String customerId, java.sql.Date date, java.sql.Time time, double total, Customer customer, List<OrderDetails> orderDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        Date = date;
        Time = time;
        this.total = total;
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    public java.sql.Time getTime() {
        return Time;
    }

    public void setTime(java.sql.Time time) {
        Time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", Date=" + Date +
                ", Time=" + Time +
                ", total=" + total +
                '}';
    }
}
