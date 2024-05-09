package com.ecom.ecommerceapis.Repository;


import com.ecom.ecommerceapis.Enums.UserType;
import com.ecom.ecommerceapis.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //return all buyers
    List<User> findByUserType(UserType userType);
}
