package org.zte;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zte.dao.BookDao;
import org.zte.dao.UserDao;
import org.zte.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

        //3.获取IoC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //4.获取bean
//        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
//        bookDao.save();
//        BookService bookService = (BookService) ctx.getBean("bookService");
//        bookService.save();
        UserDao userDao = (UserDao) ctx.getBean("userDao");
        userDao.save();
    }
}
