package com.client.clientservice.service;

import java.util.List;

import com.client.clientservice.entity.User;

public interface IClientService {
    public User findByEmailAndPassword(String email, String password);

    public List<User> findClientAll();

    public User createClient(User client, String districtId);

    public User updateClient(User client, String districtId);

    public User deleteClient(User client);

    public User getClient(Long id);
}
