package com.hiring.hiringservice.service.impl;

import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.entity.StatusContract;
import com.hiring.hiringservice.repository.IContractRepository;
import com.hiring.hiringservice.repository.IStatusContractRepository;
import com.hiring.hiringservice.service.IStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StatusServiceImpl implements IStatusService {
   @Autowired
    IContractRepository contractRepository;
    @Autowired
    IStatusContractRepository statusContractRepository;

    @Override
    public Contract updateStatusMode(Long id, String status) {
        Contract contract = contractRepository.findById(id).get();
        contract.getStatus().setStatus(status);
        return contract;
    }
}
