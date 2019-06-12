package com.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class bookShopServiceImpl implements bookShopService {

	@Autowired
    private BookShopDAO bookShopDao;
	
	//添加事务注解
	//使用Propagation指定事务的传播行为，即当前的事务方法被另外一个事务方法调用时
	//默认为REQUIRED，即使用调用方法的事务
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void purchase(int userId, int isbn) {
		//1:获取书的单价
		int price=bookShopDao.findBookPricebyIsbn(isbn);
		//2：更新书的库存
		bookShopDao.updateBookStock(isbn);
		//3：更新书的余额
		bookShopDao.updateUserAccount(userId, price);
	}

}
