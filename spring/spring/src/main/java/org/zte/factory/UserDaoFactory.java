package org.zte.factory;

import org.zte.dao.UserDao;
import org.zte.dao.impl.UserDaoImpl;

public class UserDaoFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
