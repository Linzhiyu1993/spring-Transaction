package com.spring.tx;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class springTransactionTest {
    private ApplicationContext ctx=new ClassPathXmlApplicationContext("springApplicationContext.xml");
    BookShopDAO bookShop=(BookShopDAOImpl)ctx.getBean("bookshopDAO");
    
    private bookShopService bookShopService=null;
    private Cashier cashier=null;
    
    {
    	bookShopService=(bookShopService)ctx.getBean("bookShopService");
    	cashier=(Cashier)ctx.getBean("Cashier");
    }
   
    @Test
    public void findPricebyisbnTest()
    {
    	System.out.println("book price "+bookShop.findBookPricebyIsbn(1001));
    }
    
    @Test
    public void updateBookStock()
    {
    	
    	bookShop.updateBookStock(1001);
    }
    
    @Test
    public void updateUserAccount()
    {
    	bookShop.updateUserAccount(1,100000);
    }
    
    @Test
    public void testBookShopService() {
    	
    	bookShopService.purchase(1, 1001);
    }
    
      @Test
      public void testTransactionPropagation() {
    	  cashier.checkout(2, Arrays.asList(1001,1002));
      }
    
}
