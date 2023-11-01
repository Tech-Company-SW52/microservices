package com.personalData.PersonalData.PersonalDataService.service.impl;

import com.personalData.PersonalData.PersonalDataService.client.CarrierClient;
import com.personalData.PersonalData.PersonalDataService.client.ClientClient;
import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import com.personalData.PersonalData.PersonalDataService.model.CarrierData;
import com.personalData.PersonalData.PersonalDataService.model.ClientData;
import com.personalData.PersonalData.PersonalDataService.repository.IPersonalDataRepository;
import com.personalData.PersonalData.PersonalDataService.service.IPersonalDataService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonalDataServiceImpl implements IPersonalDataService {

    @Autowired
    private IPersonalDataRepository personalDataRepository;

    @Autowired
    private ClientClient clientClient;

    @Autowired
    private CarrierClient carrierClient;


    @Override
    public PersonalData getPersonalData(String userType, Long id) {
        return personalDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public PersonalData updatePersonalData(String userType, Long id, PersonalData personalData) {
        // Obtener datos existentes
        PersonalData existingData = getPersonalData(userType, id);

        // Mapear datos personales
        updatePersonalDataFields(existingData, personalData);

        // Sincronizar con microservicios externos
        if ("carrier".equals(userType)) {
            syncCarrierData(id, existingData);
        } else if ("client".equals(userType)) {
            syncClientData(id, existingData);
        } else {
            throw new IllegalArgumentException("Tipo de usuario no válido: " + userType);
        }

        // Guardar datos actualizados en el repositorio
        return personalDataRepository.save(existingData);
    }

    @Override
    public CarrierData syncCarrierData(Long userId, PersonalData personalData) {
        //return carrierClient.updateCarrierData(userId, personalData.getCarrierData());
        return null;
    }

    @Override
    public ClientData syncClientData(Long userId, PersonalData personalData) {
        //return clientClient.updateClientData(userId, personalData.getClientData());
        return null;
    }

    private void updatePersonalDataFields(PersonalData existingData, PersonalData newData) {
        // Actualizar datos personales
        existingData.setFirstName(newData.getFirstName());
        existingData.setLastName(newData.getLastName());
        existingData.setUsername(newData.getUsername());
        existingData.setPhotoUrl(newData.getPhotoUrl());
        existingData.setEmail(newData.getEmail());
        existingData.setPassword(newData.getPassword());
        existingData.setPhone(newData.getPhone());
        existingData.setRegion(newData.getRegion());
        existingData.setBirthdate(newData.getBirthdate());
        existingData.setDescription(newData.getDescription());
        existingData.setClientData(newData.getClientData());
        existingData.setCarrierData(newData.getCarrierData());
    }

}
