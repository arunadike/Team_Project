package com.Project3.Project3.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.model.Users;
import com.Project3.Project3.repository.TravelPackageRepository;
import com.Project3.Project3.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.CartItems;
import com.Project3.Project3.repository.CartItemsRepository;

import jakarta.validation.Valid;

@Service
public class CartItemsService {

	private static final Logger logger = LoggerFactory.getLogger(CartItemsService.class);

	@Autowired
	private CartItemsRepository cartItemsRepository;

	@Autowired
	private UserRepository userRepository; // You'll need a UserRepository

	@Autowired
	private TravelPackageRepository packageRepository;

	public CartItems addItemToCart(Long userId, int packageId, Date startDate2, Integer noOfPersons, Boolean insurance, Double price) {
		try {
			Optional<Users> userOptional = userRepository.findById(userId);
			Optional<TravelPackage> packageOptional = packageRepository.findById(packageId);
			logger.info("Package present: {}", packageOptional.isPresent());
			if (userOptional.isPresent() && packageOptional.isPresent()) {
				Users user = userOptional.get();
				TravelPackage package1 = packageOptional.get(); // Renamed

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust pattern if needed
				Date startDate = null;
				try {
					//startDate = dateFormat.parse(startDate2);
					logger.info("hello");
				} catch (Exception e) {
					// Handle the parsing exception appropriately (e.g., log, throw custom exception)
					logger.error("Error parsing date: {}", startDate2);
					return null;
				}

				//CartItems cartItem = new CartItems(user, package1, startDate, noOfPersons, insurance, price);
				CartItems cartItems = new CartItems();
				cartItems.setNoOfPersons(noOfPersons);
				cartItems.setInsurance(insurance);
				cartItems.setPackage1(package1);
				cartItems.setUser(user);
				cartItems.setPrice(price);
				cartItems.setStartDate(new Date());
				return cartItemsRepository.save(cartItems);
			}
			return null; // Or throw an exception indicating user or package not found
		} catch (Exception e) {
			logger.error("Error adding item to cart: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void saveData(CartItems cartItems) {
		try {
			cartItemsRepository.save(cartItems);
		} catch (Exception e) {
			logger.error("Error saving cart item: {}", e.getMessage(), e);
			throw e;
		}
	}

	public List<CartItems> returnData() {
		try {
			return (List<CartItems>) cartItemsRepository.findAll();
		} catch (Exception e) {
			logger.error("Error fetching cart items: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void cartSave(@Valid CartItems cartItems) {
		try {
			cartItemsRepository.save(cartItems);
		} catch (Exception e) {
			logger.error("Error saving cart item: {}", e.getMessage(), e);
			throw e;
		}
	}

	public List<CartItems> cartReturn() {
		try {
			return (List<CartItems>) cartItemsRepository.findAll();
		} catch (Exception e) {
			logger.error("Error fetching cart items: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void deleteCartItem(int cartItemId) {
		try {
			cartItemsRepository.deleteById(cartItemId);
		} catch (Exception e) {
			logger.error("Error deleting cart item with ID {}: {}", cartItemId, e.getMessage(), e);
			throw e;
		}
	}

	public void updateCartItem(int cartItemId,int noOfPersons) {
		try {
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
		} catch (Exception e) {
			logger.error("Error updating cart item with ID {}: {}", cartItemId, e.getMessage(), e);
			throw e;
		}
	}

	public boolean updateInsurance(int cartItemId, Boolean hasInsurance) {
		try {
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
		} catch (Exception e) {
			logger.error("Error updating insurance for cart item with ID {}: {}", cartItemId, e.getMessage(), e);
			throw e;
		}
	}

	public void updateCart(int cartItemId,int noOfPersons,boolean insurance) {
		try {
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
		} catch (Exception e) {
			logger.error("Error updating cart with ID {}: {}", cartItemId, e.getMessage(), e);
			throw e;
		}
	}

	public List<CartItems> cartItemsById(int userId) {
		try {
			logger.info("Fetching cart items for user ID: {}", userId);
			return cartItemsRepository.findByUser_Userid(userId);
		} catch (Exception e) {
			logger.error("Error fetching cart items for user ID {}: {}", userId, e.getMessage(), e);
			throw e;
		}
	}

	public CartItems getCartItemById(int cartItemId) {
		try {
			Optional<CartItems> optionalCartItem = cartItemsRepository.findById(cartItemId);
			return optionalCartItem.orElse(null);
		} catch (Exception e) {
			logger.error("Error fetching cart item with ID {}: {}", cartItemId, e.getMessage(), e);
			throw e;
		}
	}

}
