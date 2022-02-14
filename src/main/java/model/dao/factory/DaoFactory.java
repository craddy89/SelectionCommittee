package model.dao.factory;

import model.dao.AbstractDao;

public class DaoFactory {
    public static AbstractDao getDao(DaoType type){
        return type.getDao();
    }
}
