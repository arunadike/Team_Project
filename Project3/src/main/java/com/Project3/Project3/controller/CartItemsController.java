package com.Project3.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.CartItems;
import com.Project3.Project3.service.CartItemsService;

import jakarta.validation.Valid;

@RestController
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
}