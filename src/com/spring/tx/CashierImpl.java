package com.spring.tx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Cashier")
public class CashierImpl implements Cashier {
    @Autowired
	private bookShopService bookShopService;
	
    @Transactional//事务的传播
	@Override
	public void checkout(int userId, List<Integer> isbns) {
		for(int isbn:isbns) {
			bookShopService.purchase(userId, isbn);
		}
	}

}
