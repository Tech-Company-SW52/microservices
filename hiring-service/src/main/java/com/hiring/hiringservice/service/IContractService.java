package com.hiring.hiringservice.service;

import com.hiring.hiringservice.entity.Contract;

import java.util.List;

public interface IContractService {
    List<Contract> findContractAll();
    List<Contract> findOfferContract(Long userId);
    List<Contract> findPendingContract(Long userId, String user);
    List<Contract> findHistoryContract(Long userId, String user);
    Contract createContract(Long carrierId, Long ClientId, Contract contract);
}
