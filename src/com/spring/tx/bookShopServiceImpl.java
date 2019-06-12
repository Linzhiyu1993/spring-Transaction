package com.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookShopService")
public class bookShopServiceImpl implements bookShopService {

	@Autowired
    private BookShopDAO bookShopDao;
	
	@Override
	public void purchase(int userId, int isbn) {
		//1:获取书的单价
		int price=bookShopDao.findBookPricebyIsbn(isbn);
		System.out.println(price);
		//2：更新书的库存
		bookShopDao.updateBookStock(isbn);
		//3：更新书的余额
		bookShopDao.updateUserAccount(userId, price);
	}

}
