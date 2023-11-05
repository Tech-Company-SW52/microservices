package com.client.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.client.clientservice.entity.User;

@Repository
public interface IClientRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
}
