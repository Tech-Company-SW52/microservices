package com.client.clientservice.repository;

import com.client.clientservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    Client findByEmailAndPassword(String email, String password);
}
