package com.hiring.hiringservice.service;

import com.hiring.hiringservice.entity.Contract;

import java.util.List;
import java.util.Optional;

public interface IContractService {
    List<Contract> findContractAll();
    List<Contract> findOfferContract(Long userId);
    List<Contract> findPendingContract(Long userId, String user);
    List<Contract> findHistoryContract(Long userId, String user);
    Contract createContract(Long carrierId, Long ClientId, Contract contract);
    List<Contract> findContractsByCarrierId(Long carrierId);
    List<Contract> findByStatusStatusAndCarrierId(String status, Long id);
    List<Contract> findByCarrierId(Long id);
    Optional<Contract> getById(Long id);
    Contract save(Contract contract);

}
