package com.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookshopDAO")
public class BookShopDAOImpl implements BookShopDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findBookPricebyIsbn(int isbn) {
		String sql="select price from book where isbn=?";
		return jdbcTemplate.queryForObject(sql, Integer.class,isbn);
	}

	@Override
	public void updateBookStock(int isbn) {
        //检查书的库存是否不够，若不够则抛出异常
    	String sql2="select stock from book where isbn=?";
    	int stock=jdbcTemplate.queryForObject(sql2,Integer.class,isbn);
    	if(stock==0)
    	{
    		throw new bookStockException("库存不足！");
    	}
		String sql="update book_stock set stock=stock-1 where isbn=?";
		jdbcTemplate.update(sql, isbn);
	}

	@Override
	public void updateUserAccount(int userId, int Price) {
		
		//验证余额
    	String sql2="select balance from book where id=?";
    	int stock=jdbcTemplate.queryForObject(sql2,Integer.class,userId);
    	if(stock==0)
    	{
    		throw new userAccountException("余额不足！");
    	}
		String sql="update account set balance=balance-? where id=?";
		this.jdbcTemplate.update(sql,Price,userId);
		
		System.out.println("update account set balance=balance-"+Price+" where id="+userId);
	}
	
}
