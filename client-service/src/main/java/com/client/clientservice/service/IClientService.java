package com.client.clientservice.service;

import com.client.clientservice.entity.Client;

import java.util.List;

public interface IClientService {
    public Client findByEmailAndPassword(String email, String password);
    public List<Client> findClientAll();
    public Client createClient(Client client);
    public Client updateClient(Client client);
    public Client deleteClient(Client client);
    public Client getClient(Long id);
}
