package com.Project3.Project3.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.CartItems;
import com.Project3.Project3.repository.CartItemsRepository;

import jakarta.validation.Valid;

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

	public void cartSave(@Valid CartItems cartItems) {
		// TODO Auto-generated method stub
		cartItemsRepository.save(cartItems);

	}

	public List<CartItems> cartReturn() {
		// TODO Auto-generated method stub
		return (List<CartItems>) cartItemsRepository.findAll();
		//return null;
	}

	public void deleteCartItem(int cartItemId) {
		// TODO Auto-generated method stub
		cartItemsRepository.deleteById(cartItemId);

	}

	public void updateCartItem(int cartItemId,int noOfPersons) {
		// TODO Auto-generated method stub
		 Optional<CartItems> optionalCartItem = cartItemsRepository.findById(cartItemId);
		   if (optionalCartItem.isEmpty()) {
		    throw new NoSuchElementException("Cart item with ID " + cartItemId + " not found");
		   }
		   CartItems cartItem = optionalCartItem.get();

		   if (noOfPersons > 0) {
		    cartItem.setNoOfPersons(noOfPersons);
		   } else if (noOfPersons <= 0) {
		    throw new IllegalArgumentException("Number of persons must be greater than zero.");
		   }


		    	   double originalPrice = cartItem.getPackage1().getPrice();
		    	    double newPrice = originalPrice*noOfPersons;
		    	   cartItem.setPrice(newPrice);


		    cartItemsRepository.save(cartItem);

	}

}
