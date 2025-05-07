package com.Project3.Project3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Project3.Project3.model.CartItems;
import com.Project3.Project3.service.CartItemsService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartItemsController {

	private static final Logger logger = LoggerFactory.getLogger(CartItemsController.class);

	@Autowired
	CartItemsService cartItemsService;

	@GetMapping("/cartGet/{userId}")
	public ResponseEntity<List<CartItems>> cartGet(@PathVariable int userId) {
		logger.info("Fetching cart items for user ID: {}", userId);
		try {
			List<CartItems> cartItems = cartItemsService.cartItemsById(userId);
			if (cartItems != null && !cartItems.isEmpty()) {
				logger.info("Successfully retrieved {} cart items for user ID: {}", cartItems.size(), userId);
				return new ResponseEntity<>(cartItems, HttpStatus.OK);
			} else {
				logger.warn("No cart items found for user ID: {}", userId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Or return an empty list with 200 OK
			}
		} catch (Exception e) {
			logger.error("Error fetching cart items for user ID: {}", userId, e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving cart items", e);
		}
	}

	@GetMapping("/cartGet/item/{cartItemId}")
	public ResponseEntity<CartItems> cartGetByItemId(@PathVariable int cartItemId) {
		logger.info("Fetching cart item with ID: {}", cartItemId);
		try {
			CartItems cartItem = cartItemsService.getCartItemById(cartItemId);
			if (cartItem != null) {
				logger.info("Successfully retrieved cart item with ID: {}", cartItemId);
				return new ResponseEntity<>(cartItem, HttpStatus.OK);
			} else {
				logger.warn("No cart item found with ID: {}", cartItemId);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Error fetching cart item with ID: {}", cartItemId, e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving cart item", e);
		}
	}

	@DeleteMapping("/delete/{cartItemId}")
	public void deleteItemsFromCart(@PathVariable int cartItemId) throws Exception {
		logger.info("Received request to delete cart item with ID: {}", cartItemId);
		try {
			cartItemsService.deleteCartItem(cartItemId);
			logger.info("Successfully deleted cart item with ID: {}", cartItemId);
		} catch (EmptyResultDataAccessException e) {
			logger.warn("Attempted to delete non-existent cart item with ID: {}", cartItemId);
			throw new Exception("Cart item with ID " + cartItemId + " not found.", e);
		} catch (Exception e) {
			logger.error("An error occurred while deleting cart item with ID {}: {}", cartItemId, e.getMessage(), e);
			throw new RuntimeException("Failed to delete cart item with ID " + cartItemId, e);
		}
	}

	@PutMapping("/updateCart/{cartItemId}")
	public void updateCartItems(@PathVariable int cartItemId, @RequestBody UpdateCartRequest updateCartRequest) {
		cartItemsService.updateCart(cartItemId, updateCartRequest.getNoOfPersons(), updateCartRequest.getInsurance());
	}

	@PostMapping("/add")
	public ResponseEntity<CartItems> addItemToCart(@RequestBody Map<String, Object> payload) {
		logger.info("Received request to add item to cart with payload: {}", payload);
		Long userId = null;
		Integer packageId = null;
		Date startDate = null;
		Integer noOfPersons = null;
		Boolean insurance = null;
		Double price = null;

		try {
			// Extract data from the request body with null checks and proper casting
			if (payload.containsKey("user") && payload.get("user") instanceof Map) {
				Object userIdObj = ((Map<?, ?>) payload.get("user")).get("userId");
				if (userIdObj != null) {
					userId = Long.parseLong(userIdObj.toString());
				} else {
					logger.warn("User ID is missing in the payload.");
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else {
				logger.warn("User information is missing or in incorrect format in the payload.");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			if (payload.containsKey("package1") && payload.get("package1") instanceof Map) {
				Object packageIdObj = ((Map<?, ?>) payload.get("package1")).get("packageId");
				if (packageIdObj != null) {
					packageId = Integer.parseInt(packageIdObj.toString());
				} else {
					logger.warn("Package ID is missing in the payload.");
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else {
				logger.warn("Package information is missing or in incorrect format in the payload.");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			Object startDateObj = payload.get("startDate");
			if (startDateObj != null) {
				String startDateStr = startDateObj.toString();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust pattern if needed
				try {
					startDate = dateFormat.parse(startDateStr);
				} catch (ParseException e) {
					logger.error("Error parsing date: {}", startDateStr, e);
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Indicate invalid date format
				}
			} else {
				logger.warn("Start date is missing in the payload.");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			Object noOfPersonsObj = payload.get("noOfPersons");
			if (noOfPersonsObj != null) {
				if (noOfPersonsObj instanceof Integer) {
					noOfPersons = (Integer) noOfPersonsObj;
				} else {
					logger.warn("Number of persons is in incorrect format in the payload: {}", noOfPersonsObj);
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else {
				logger.warn("Number of persons is missing in the payload.");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			Object insuranceObj = payload.get("insurance");
			if (insuranceObj != null) {
				if (insuranceObj instanceof Boolean) {
					insurance = (Boolean) insuranceObj;
				} else {
					logger.warn("Insurance is in incorrect format in the payload: {}", insuranceObj);
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else {
				logger.warn("Insurance information is missing in the payload.");
			}

			Object priceObj = payload.get("price");
			if (priceObj != null) {
				try {
					price = Double.parseDouble(priceObj.toString());
				} catch (NumberFormatException e) {
					logger.error("Error parsing price: {}", priceObj, e);
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else {
				logger.warn("Price is missing in the payload.");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			CartItems addedItem = cartItemsService.addItemToCart(userId, packageId, startDate, noOfPersons, insurance,
					price);

			if (addedItem != null) {
				logger.info("Successfully added item to cart: {}", addedItem);
				return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
			} else {
				logger.warn("Failed to add item to cart for user ID: {}, package ID: {}", userId, packageId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Or another appropriate status
			}

		} catch (NumberFormatException e) {
			logger.error("Error parsing numerical value in payload: {}", payload, e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (ClassCastException e) {
			logger.error("Error casting payload data to the expected type: {}", payload, e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("An unexpected error occurred while adding item to cart with payload: {}", payload, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Indicate a server-side error
		}
	}
}

class UpdateCartRequest {
	private Integer noOfPersons;
	private Double price;
	private boolean insurance;

	public Integer getNoOfPersons() {
		return noOfPersons;
	}

	public void setNoOfPersons(Integer noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Boolean getInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

}
