package org.zte.service.impl;

import org.zte.dao.BookDao;
import org.zte.dao.UserDao;
import org.zte.service.BookService;

public class UserServiceImpl implements BookService {

    //5.删除业务层中使用new方式创建的dao对象
//    private BookDao bookDao = new BookDaoImpl();
    private UserDao userDao;


    @Override
    public void save() {
        System.out.println("userService save ....");
        userDao.save();
    }

    //6.提供对应的set方法
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
