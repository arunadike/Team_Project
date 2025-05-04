package com.Project3.Project3.repository;
 
import java.util.List;
 
import org.springframework.data.repository.CrudRepository;
 
import com.Project3.Project3.model.CartItems;
import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.model.Users;
 
 
public interface CartItemsRepository extends CrudRepository<CartItems,Integer>{
 
	List<CartItems> findByUser_Userid(long userid);
	boolean existsByUserAndPackage1(Users user, TravelPackage package1);
 
}