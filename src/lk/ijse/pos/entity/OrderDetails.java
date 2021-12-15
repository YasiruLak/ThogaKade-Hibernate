package lk.ijse.pos.entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public class OrderDetails implements SuperEntity {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "itemCode", referencedColumnName = "code")
    private Item itemCode;
    @ManyToOne
    @JoinColumn(referencedColumnName = "qty")
    private Item qty;
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "oId")
    private Orders order;

    public OrderDetails() {
    }

    public OrderDetails(String id, Item itemCode, Item qty, Orders order) {
        this.id = id;
        this.itemCode = itemCode;
        this.qty = qty;
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item getItemCode() {
        return itemCode;
    }

    public void setItemCode(Item itemCode) {
        this.itemCode = itemCode;
    }

    public Item getQty() {
        return qty;
    }

    public void setQty(Item qty) {
        this.qty = qty;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id='" + id + '\'' +
                ", itemCode=" + itemCode +
                ", qty=" + qty +
                ", order=" + order +
                '}';
    }
}
