package lk.ijse.pos.dao;

import lk.ijse.pos.entity.SuperEntity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author : Yasiru Dahanayaka
 * @since : 0.1.0
 **/
public interface SuperDAO<Entity extends SuperEntity, ID extends Serializable>{
    boolean add(Entity entity ) throws Exception;

    boolean delete(ID id) throws Exception;

    boolean update(Entity entity) throws Exception;

    Entity search(ID id) throws Exception;

    ArrayList<Entity> getAll() throws Exception;
}
