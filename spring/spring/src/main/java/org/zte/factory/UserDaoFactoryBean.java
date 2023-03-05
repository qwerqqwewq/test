package org.zte.factory;

import org.springframework.beans.factory.FactoryBean;
import org.zte.dao.UserDao;

public class UserDaoFactoryBean implements FactoryBean<UserDao> {
    //代替原始实例工厂中创建对象的方法
    @Override
    public UserDao getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }
}
