package lk.ijse.pos.entity;

import java.sql.Date;
import java.sql.Time;

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

    public Orders() {
    }

    public Orders(String orderId, String customerId, java.sql.Date date, java.sql.Time time, double total) {
        this.orderId = orderId;
        this.customerId = customerId;
        Date = date;
        Time = time;
        this.total = total;
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
