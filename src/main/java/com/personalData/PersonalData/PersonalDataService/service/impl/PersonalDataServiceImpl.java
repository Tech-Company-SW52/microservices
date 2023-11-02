package com.personalData.PersonalData.PersonalDataService.service.impl;

import com.personalData.PersonalData.PersonalDataService.client.CarrierClient;
import com.personalData.PersonalData.PersonalDataService.client.ClientClient;
import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import com.personalData.PersonalData.PersonalDataService.model.CarrierData;
import com.personalData.PersonalData.PersonalDataService.model.ClientData;
import com.personalData.PersonalData.PersonalDataService.service.IPersonalDataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// Esta clase implementa la interfaz IPersonalDataService y define los métodos para manejar los datos personales.
@Service
public class PersonalDataServiceImpl implements IPersonalDataService {

    private static final String CARRIER = "carrier";
    private static final String CLIENT = "client";

    private final ClientClient clientClient;
    private final CarrierClient carrierClient;
    private final ModelMapper modelMapper;

    public PersonalDataServiceImpl(ClientClient clientClient, CarrierClient carrierClient, ModelMapper modelMapper) {
        this.clientClient = clientClient;
        this.carrierClient = carrierClient;
        this.modelMapper = modelMapper;
    }

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
        // En lugar de obtener datos existentes, simplemente sincronizamos con los otros servicios
        switch (userType) {
            case CARRIER:
                return convertToPersonalData(syncCarrierData(id, personalData));
            case CLIENT:
                return convertToPersonalData(syncClientData(id, personalData));
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido: " + userType);
        }
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
        BeanUtils.copyProperties(newData, existingData, "id"); // Ignora el campo "id"
    }

    private PersonalData convertToPersonalData(CarrierData carrierData) {
        return modelMapper.map(carrierData, PersonalData.class);
    }

    private PersonalData convertToPersonalData(ClientData clientData) {
        return modelMapper.map(clientData, PersonalData.class);
    }
}
