package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pos.bo.custom.impl.ItemBOImpl;
import lk.ijse.pos.bo.custom.impl.OrderBOImpl;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        if (boFactory == null){
            boFactory =  new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types){
        switch (types){
            case ITEM:
                return new ItemBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case ORDER:
                return new OrderBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes{
        CUSTOMER, ITEM, ORDER
    }
}
