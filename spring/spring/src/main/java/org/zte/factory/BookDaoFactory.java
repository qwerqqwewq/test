package org.zte.factory;

import org.zte.dao.BookDao;
import org.zte.dao.impl.BookDaoImpl;

public class BookDaoFactory {
    public static BookDao getBookDao(){
        return new BookDaoImpl();
    }
}
