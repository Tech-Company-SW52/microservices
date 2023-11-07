package com.fastporte.carrierservice.repository;

import com.fastporte.carrierservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarrierRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
}