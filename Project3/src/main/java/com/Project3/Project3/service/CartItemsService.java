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

<<<<<<< HEAD
}
=======

	public boolean updateInsurance(int cartItemId, Boolean hasInsurance) {
		// TODO Auto-generated method stub
		Optional<CartItems> optionalCartItem = cartItemsRepository.findById(cartItemId);
        if (optionalCartItem.isPresent()) {
            CartItems cartItem = optionalCartItem.get();
            cartItem.setInsurance(hasInsurance);
            // Recalculate the price based on insurance (you'll need your pricing logic here)
            double basePrice = cartItem.getPrice();// ... get the base price of the item ...
            double insuranceCost = hasInsurance ? 500.00 : 0.00; // Example insurance cost
            cartItem.setPrice(basePrice + insuranceCost);
            cartItemsRepository.save(cartItem);
            return true;
        }
        return false;
		
		//return false;
	}

	public void updateCart(int cartItemId,int noOfPersons,boolean insurance) {
		// TODO Auto-generated method stub
		Optional<CartItems> optionalCartItem=cartItemsRepository.findById(cartItemId);
		if(optionalCartItem.isPresent())
		{
			CartItems cartItem = optionalCartItem.get();
			cartItem.setNoOfPersons(noOfPersons);
			cartItem.setInsurance(insurance);
			double price1=cartItem.getPackage1().getPrice()*cartItem.getNoOfPersons();
			double c=0;
			//cartItem.setInsurance(cartItem.isInsurance());
			if(cartItem.isInsurance())
			{
				c=500*cartItem.getNoOfPersons();
			}
			//cartItem.setInsurance(cartItem.isInsurance());
			cartItem.setPrice(price1+c);
			//cartItem.setNoOfPersons(cartItem.getNoOfPersons());
			
			cartItemsRepository.save(cartItem);
		}
		
		
	}
	
}
>>>>>>> master
