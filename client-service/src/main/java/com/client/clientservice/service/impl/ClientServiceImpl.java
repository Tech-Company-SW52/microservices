package com.client.clientservice.service.impl;

import com.client.clientservice.entity.Client;
import com.client.clientservice.repository.IClientRepository;
import com.client.clientservice.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    IClientRepository clientRepository;

    @Override
    public Client findByEmailAndPassword(String email, String password) {
        return clientRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<Client> findClientAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        Client clientDB = clientRepository.findByEmailAndPassword(client.getEmail(), client.getPassword());
        if (clientDB != null) {
            return clientDB;
        }
        clientDB = clientRepository.save(client);
        return clientDB;
    }

    @Override
    public Client updateClient(Client client) {
        Client clientDB = getClient(client.getId());
        if (clientDB == null) {
            return null;
        }
        clientDB.setFirstName(client.getFirstName());
        clientDB.setLastName(client.getLastName());
        clientDB.setEmail(client.getEmail());
        clientDB.setPassword(client.getPassword());
        clientDB.setPhotoUrl(client.getPhotoUrl());
        clientDB.setDescription(client.getDescription());
        clientDB.setBirthdate(client.getBirthdate());
        return clientRepository.save(clientDB);
    }

    @Override
    public Client deleteClient(Client client) {
        Client clientDB = getClient(client.getId());
        if (clientDB == null) {
            return null;
        }
        clientRepository.deleteById(client.getId());
        return clientDB;
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
}
