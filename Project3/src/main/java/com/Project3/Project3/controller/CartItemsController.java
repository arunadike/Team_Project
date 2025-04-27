package com.Project3.Project3.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.CartItems;
import com.Project3.Project3.service.CartItemsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartItemsController {

	@Autowired
	CartItemsService cartItemsService;

	@GetMapping("/cartItemsGet")
	public List<CartItems> cartItemsGet(){
		return cartItemsService.returnData();
	}

	@PostMapping("/cartItemsPost")
	public void cartItemsPost(@RequestBody @Valid CartItems cartItems) {
		cartItemsService.saveData(cartItems);
	}
	@PostMapping("/cartPost")
	public void cartPost(@RequestBody @Valid CartItems cartItems)
	{
		cartItemsService.cartSave(cartItems);
	}
	@GetMapping("/cartGet")
	public List<CartItems> cartGet()
	{
		return cartItemsService.cartReturn();
	}
	@DeleteMapping("/delete/{cartItemId}")
	public void cartDelete(@PathVariable int cartItemId)
	{
		cartItemsService.deleteCartItem(cartItemId);
	}

	@PutMapping("/update/{cartItemId}")
	public void cartUpdate(@PathVariable int cartItemId, @RequestBody UpdateCartRequest updateCartRequest)
	{
		cartItemsService.updateCartItem(cartItemId,updateCartRequest.getNoOfPersons());
	}
	
	 @PutMapping("/insuranceUpdate/{cartItemId}")
	    public void updateInsurance(@PathVariable int cartItemId, @RequestBody Map<String, Boolean> requestBody) {
	        Boolean hasInsurance = requestBody.get("hasInsurance");
	        if (hasInsurance == null) {
	            System.out.println("Missing 'hasInsurance' in request body");
	        }

	        boolean updated = cartItemsService.updateInsurance(cartItemId, hasInsurance);
	        
	    }
	 @PutMapping("/updateCart/{cartItemId}")
	 public void updateCartItems(@PathVariable int cartItemId,@RequestBody UpdateCartRequest updateCartRequest)
	 {
		 cartItemsService.updateCart(cartItemId,updateCartRequest.getNoOfPersons(),updateCartRequest.getInsurance());
	 }
}
class UpdateCartRequest
{
	private Integer noOfPersons;
	private Double price;
	private boolean insurance;

	  public Integer getNoOfPersons() {
	   return noOfPersons;
	  }

	  public void setNoOfPersons(Integer noOfPersons) {
	   this.noOfPersons = noOfPersons;
	  }
	  public double getPrice()
	  {
		  return price;
	  }
	  public void setPrice(double price)
	  {
		  this.price=price;
	  }
<<<<<<< HEAD
}
=======
	  public Boolean getInsurance()
	  {
		  return insurance;
	  }
	  public void setInsurance(boolean insurance)
	  {
		  this.insurance=insurance;
	  }
}
>>>>>>> master
