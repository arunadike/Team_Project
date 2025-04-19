package com.Project3.Project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.CartItems;
import com.Project3.Project3.repository.CartItemsRepository;

@Service
public class CartItemsService {
	
	@Autowired
	private CartItemsRepository cartItemsRepository;
	
	public void saveData(CartItems cartItems) {
		cartItemsRepository.save(cartItems);
		
	}

	public List<CartItems> returnData() {
		// TODO Auto-generated method stub
		return (List<CartItems>) cartItemsRepository.findAll();
	}
	
}