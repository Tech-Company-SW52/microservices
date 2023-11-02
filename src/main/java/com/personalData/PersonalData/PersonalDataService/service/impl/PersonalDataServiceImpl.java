package com.personalData.PersonalData.PersonalDataService.service.impl;

import com.personalData.PersonalData.PersonalDataService.client.CarrierClient;
import com.personalData.PersonalData.PersonalDataService.client.ClientClient;
import com.personalData.PersonalData.PersonalDataService.model.CarrierData;
import com.personalData.PersonalData.PersonalDataService.model.ClientData;
import com.personalData.PersonalData.PersonalDataService.service.IPersonalDataService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Esta clase implementa la interfaz IPersonalDataService y define los métodos para manejar los datos personales.
 * Se encarga de la lógica de negocio relacionada con los datos personales de usuarios tipo "carrier" y "client".
 * Dependiendo del tipo de usuario, esta clase interactúa con los clientes correspondientes (CarrierClient o ClientClient)
 * para obtener y actualizar los datos personales a través de servicios externos.
 */
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
    public Object getPersonalData(String userType, Long id) {
        switch (userType) {
            case CARRIER:
                ResponseEntity<CarrierData> carrierResponse = carrierClient.getCarrierData(id);
                return carrierResponse.getBody();
            case CLIENT:
                ResponseEntity<ClientData> clientResponse = clientClient.getClientData(id);
                return clientResponse.getBody();
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido: " + userType);
        }
    }

    @Override
    public Object updatePersonalData(String userType, Long id, Object personalData) {
        switch (userType) {
            case CARRIER:
                return carrierClient.updateCarrier(id, (CarrierData) personalData).getBody();
            case CLIENT:
                return clientClient.updateClient(id, (ClientData) personalData).getBody();
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido: " + userType);
        }
    }
}
