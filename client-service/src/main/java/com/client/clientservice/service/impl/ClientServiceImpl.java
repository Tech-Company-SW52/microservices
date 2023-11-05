package com.client.clientservice.service.impl;

import com.client.clientservice.entity.User;
import com.client.clientservice.entity.Type;
import com.client.clientservice.repository.IClientRepository;
import com.client.clientservice.service.IClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    IClientRepository clientRepository;

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return clientRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<User> findClientAll() {
        List<User> clientsDB = clientRepository.findAll();
        clientsDB.removeIf(user -> user.getType() != Type.CLIENT);
        return clientsDB;
    }

    @Override
    public User createClient(User client) {
        User clientDB = clientRepository.findByEmailAndPassword(
                client.getEmail(), client.getPassword());
        if (clientDB != null) {
            return clientDB;
        }
        client.setType(Type.CLIENT);
        clientDB = clientRepository.save(client);
        return clientDB;
    }

    @Override
    public User updateClient(User client) {
        User clientDB = getClient(client.getId());
        if (clientDB == null) {
            return null;
        }
        clientDB.setFirstName(client.getFirstName());
        clientDB.setLastName(client.getLastName());
        clientDB.setUsername(client.getUsername());
        clientDB.setEmail(client.getEmail());
        clientDB.setPassword(client.getPassword());
        clientDB.setBirthdate(client.getBirthdate());
        clientDB.setDescription(client.getDescription());
        clientDB.setPhotoUrl(client.getPhotoUrl());
        clientDB.setPhone(client.getPhone());
        // clientDB.setDistrictId(client.getDistrictId());
        clientDB.setStreet(client.getStreet());
        return clientRepository.save(clientDB);
    }

    @Override
    public User deleteClient(User client) {
        User clientDB = getClient(client.getId());
        if (clientDB == null) {
            return null;
        }
        clientRepository.deleteById(client.getId());
        return clientDB;
    }

    @Override
    public User getClient(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
}
