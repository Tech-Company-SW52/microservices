package com.hiring.hiringservice.service.impl;


import com.hiring.hiringservice.client.CarrierClient;
import com.hiring.hiringservice.client.ClientClient;
import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.model.Carrier;
import com.hiring.hiringservice.model.Client;
import com.hiring.hiringservice.repository.IContractRepository;
import com.hiring.hiringservice.repository.INotificationRepository;
import com.hiring.hiringservice.repository.IStatusContractRepository;
import com.hiring.hiringservice.service.IContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ContractServiceImpl implements IContractService {

    @Autowired
    IContractRepository contractRepository;
    @Autowired
    IStatusContractRepository statusContractRepository;
    @Autowired
    INotificationRepository notificationRepository;
    @Autowired
    ClientClient clientClient;
    @Autowired
    CarrierClient carrierClient;

    @Override
    public List<Contract> findContractAll() {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> findOfferContract(Long carrierId) {
        //Carrier carrierDB = carrierClient.getCarrier(carrierId).getBody();
        //if (carrierDB == null) return null;
        List<Contract> contractsDB = contractRepository.findAll();
        //contractsDB.removeIf(contract -> !contract.getStatus().getStatus().equals("OFFER"));
        contractsDB.removeIf(contract -> !contract.getCarrierId().equals(carrierId));
        if (!contractsDB.isEmpty())
            return null;
        return contractsDB;
    }


    public List<Contract> findContractsByCarrierId(Long carrierId) {
        List<Contract> allContracts = contractRepository.findAll();
        List<Contract> contractsByCarrierId = new ArrayList<>();

        for (Contract contract : allContracts) {
            if (contract.getCarrierId() != null && contract.getCarrierId().equals(carrierId)) {
                contractsByCarrierId.add(contract);
            }
        }

        return contractsByCarrierId;
    }


    public List<Contract> findContract(Long userId, String user, String contractType) {
        List<Contract> contractsDB = contractRepository.findAll();
        contractsDB.removeIf(contract -> !contract.getStatus().getStatus().equals(contractType));

        if (Objects.equals(user, "client"))
        {
            //Client clientDB = clientClient.getClient(userId).getBody();
            if (contractsDB.isEmpty()) {
                return null;
            }else{
                contractsDB.removeIf(contract -> !contract.getClientId().equals(userId));
            }


        }
        else{
            if (Objects.equals(user, "carrier")) {
                //Carrier carrierDB = carrierClient.getCarrier(userId).getBody();
                if (contractsDB.isEmpty())
                {
                    return null;
                }else
                {
                    contractsDB.removeIf(contract -> !contract.getCarrierId().equals(userId));
                }
            }
        }

        if (contractsDB.isEmpty())
            return null;

        return contractsDB;

    }

    @Override
    public List<Contract> findPendingContract(Long userId, String user) {
        return findContract(userId, user, "PENDING");
    }

    @Override
    public List<Contract> findHistoryContract(Long userId, String user) {
        return findContract(userId, user, "HISTORY");
    }

    @Override
    public Contract createContract(Long carrierId, Long clientId, Contract contract) {
        Carrier carrierDB = carrierClient.getCarrier(carrierId).getBody();
        Client clientDB = clientClient.getClient(clientId).getBody();
        if (carrierDB == null || clientDB == null) return null;
        contract.setCarrierId(carrierId);
        contract.setClientId(clientId);
        contract.setVisible(true);
        contract.setStatus(statusContractRepository.findByStatus("OFFER"));
        contract.setNotifications(notificationRepository.findByReadStatus(false));
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> findByStatusStatusAndCarrierId(String status, Long id){
        return contractRepository.findByStatusStatusAndCarrierId(status, id);
    }

    @Override
    public List<Contract> findByCarrierId(Long id){
        return contractRepository.findByCarrierId(id);
    }

}
