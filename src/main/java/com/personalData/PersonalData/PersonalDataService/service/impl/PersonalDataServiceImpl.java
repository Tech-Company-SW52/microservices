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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PersonalDataServiceImpl implements IPersonalDataService {

    @Autowired
    private ClientClient clientClient;

    @Autowired
    private CarrierClient carrierClient;

    @Override
    public PersonalData getPersonalData(String userType, Long id) {
        PersonalData personalData;
        switch (userType) {
            case "carrier":
                CarrierData carrierData = carrierClient.getCarrierData(id).getBody();
                personalData = convertToPersonalData(carrierData);
                break;
            case "client":
                ClientData clientData = clientClient.getClientData(id).getBody();
                personalData = convertToPersonalData(clientData);
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido: " + userType);
        }
        return personalData;
    }

    @Override
    public PersonalData updatePersonalData(String userType, Long id, PersonalData personalData) {
        PersonalData existingData = getPersonalData(userType, id);
        updatePersonalDataFields(existingData, personalData);

        switch (userType) {
            case "carrier":
                syncCarrierData(id, existingData);
                break;
            case "client":
                syncClientData(id, existingData);
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido: " + userType);
        }

        return existingData;
    }

    @Override
    public CarrierData syncCarrierData(Long userId, PersonalData personalData) {
        ResponseEntity<CarrierData> response = carrierClient.updateCarrier(userId, personalData.getCarrierData());
        return response.getBody();
    }

    @Override
    public ClientData syncClientData(Long userId, PersonalData personalData) {
        ResponseEntity<ClientData> response = clientClient.updateClient(userId, personalData.getClientData());
        return response.getBody();
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

    private PersonalData convertToPersonalData(CarrierData carrierData) {
        PersonalData personalData = new PersonalData();
        // Implementa la lógica para asignar valores de carrierData a personalData
        return personalData;
    }

    private PersonalData convertToPersonalData(ClientData clientData) {
        PersonalData personalData = new PersonalData();
        // Implementa la lógica para asignar valores de clientData a personalData
        return personalData;
    }

}
