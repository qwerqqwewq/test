package org.zte.dao.impl;

import org.zte.dao.BookDao;

public class BookDaoImpl implements BookDao {
    public BookDaoImpl() {
        System.out.println("book dao is constructor is running");
    }

    @Override
    public void save() {
        System.out.println("bookDao save ....");
    }
}
