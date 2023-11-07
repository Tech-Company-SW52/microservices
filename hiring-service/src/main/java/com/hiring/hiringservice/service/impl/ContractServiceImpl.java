package com.hiring.hiringservice.service.impl;

import com.hiring.hiringservice.client.CarrierClient;
import com.hiring.hiringservice.client.ClientClient;
import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.entity.User;
import com.hiring.hiringservice.repository.IContractRepository;
import com.hiring.hiringservice.repository.INotificationRepository;
import com.hiring.hiringservice.repository.IStatusContractRepository;
import com.hiring.hiringservice.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        List<Contract> contractsDB = contractRepository.findAll();
        contractsDB.removeIf(contract -> !contract.getStatus().getStatus().equals("OFFER"));
        User carrierDB = carrierClient.getCarrier(carrierId).getBody();
        if (carrierDB == null)
            return null;
        contractsDB.removeIf(contract -> !contract.getCarrier().equals(carrierDB));
        if (contractsDB.isEmpty())
            return null;
        return contractsDB;
    }

    public List<Contract> findContract(Long userId, String user, String contractType) {
        List<Contract> contractsDB = contractRepository.findAll();
        contractsDB.removeIf(contract -> !contract.getStatus().getStatus().equals(contractType));

        if (contractsDB.isEmpty())
            return null;

        if (Objects.equals(user, "client")) {
            User clientDB = clientClient.getClient(userId).getBody();
            contractsDB.removeIf(contract -> !contract.getClient().equals(clientDB));
        } else if (Objects.equals(user, "carrier")) {
            User carrierDB = carrierClient.getCarrier(userId).getBody();
            contractsDB.removeIf(contract -> !contract.getCarrier().equals(carrierDB));
        } else {
            return null;
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
    public Contract updateStatusContract(Long id, String statusContract) {
        Contract contractDB = contractRepository.findById(id).orElse(null);
        if (contractDB == null)
            return null;
        contractDB.setStatus(statusContractRepository.findByStatus(statusContract));
        contractRepository.save(contractDB);
        return contractDB;
    }

    @Override
    public List<Contract> getUnreadNotificationsByUser(Long userId, String user) {
        List<Contract> contractsDB = contractRepository.findAll();
        contractsDB.removeIf(
                contract -> !contract.getNotification().equals(notificationRepository.findByReadStatus(false)));
        if (user.equals("client")) {
            User clientDB = clientClient.getClient(userId).getBody();
            contractsDB.removeIf(contract -> !contract.getClient().equals(clientDB));
        } else if (user.equals("carrier")) {
            User carrierDB = carrierClient.getCarrier(userId).getBody();
            contractsDB.removeIf(contract -> !contract.getCarrier().equals(carrierDB));
        } else {
            return null;
        }
        if (contractsDB.isEmpty())
            return null;
        return contractsDB;
    }

    @Override
    public Contract changeNotificationStatus(Long id) {
        Contract contractDB = contractRepository.findById(id).orElse(null);
        if (contractDB == null)
            return null;
        contractDB.getNotification().setReadStatus(!contractDB.getNotification().isReadStatus());
        contractRepository.save(contractDB);
        return contractDB;
    }

    @Override
    public Contract changeVisibility(Long id) {
        Contract contractDB = contractRepository.findById(id).orElse(null);
        if (contractDB == null)
            return null;
        contractDB.setVisible(!contractDB.isVisible());
        contractRepository.save(contractDB);
        return contractDB;
    }

    @Override
    public Contract createContract(Long carrierId, Long clientId, Contract contract) {
        User carrierDB = carrierClient.getCarrier(carrierId).getBody();
        if (carrierDB == null)
            return null;
        User clientDB = clientClient.getClient(clientId).getBody();
        if (clientDB == null)
            return null;
        contract.setCarrier(carrierDB);
        contract.setClient(clientDB);
        contract.setVisible(true);
        contract.setStatus(statusContractRepository.findByStatus("OFFER"));
        contract.setNotification(notificationRepository.findByReadStatus(false));
        return contractRepository.save(contract);
    }

    @Override
    public Contract getById(Long id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }
}
