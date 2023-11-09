package com.hiring.hiringservice.service;

import com.hiring.hiringservice.entity.Contract;

import java.util.List;

public interface IContractService {

    List<Contract> findContractAll();

    List<Contract> findOfferContract(Long id);

    List<Contract> findPendingContract(Long userId, String user);

    List<Contract> findHistoryContract(Long userId, String user);

    Contract updateStatusContract(Long id, String statusContract);

    List<Contract> getUnreadNotificationsByUser(Long userId, String user);

    Contract changeNotificationStatus(Long id);

    Contract changeVisibility(Long id);

    Contract createContract(
            Long carrierId, Long clientId, String districtFromId,
            String districtToId, Contract contract);

    Contract getById(Long id);

    Contract save(Contract contract);
}
