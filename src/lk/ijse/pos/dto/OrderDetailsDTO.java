package lk.ijse.pos.dto;

import java.io.Serializable;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public class OrderDetailsDTO implements Serializable {
    private String orderId;
    private String itemCode;
    private double discount;
    private int qty;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String orderId, String itemCode, double discount, int qty) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.discount = discount;
        this.qty = qty;
    }

    public OrderDetailsDTO(String orderId, String code, int qty, double discount) {
        this.orderId = orderId;
        this.itemCode = code;
        this.discount = discount;
        this.qty = qty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", discount=" + discount +
                ", qty=" + qty +
                '}';
    }
}
