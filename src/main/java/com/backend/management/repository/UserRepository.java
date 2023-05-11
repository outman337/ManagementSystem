package com.backend.management.repository;

import com.backend.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.apartmentNumber.apartmentNumber = :apartment_number")
    List<User> getOccupants(
            @Param("apartment_number") String apartmentNumber);

}

