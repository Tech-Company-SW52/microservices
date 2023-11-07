package com.hiring.hiringservice.service.impl;

import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.entity.StatusContract;
import com.hiring.hiringservice.repository.IContractRepository;
import com.hiring.hiringservice.repository.IStatusContractRepository;
import com.hiring.hiringservice.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public StatusContract save(StatusContract statusContract) {
        return statusContractRepository.save(statusContract);
    }

    @Override
    public void delete(Long id) {
        statusContractRepository.deleteById(id);
    }

    @Override
    public List<StatusContract> getAll() {
        return statusContractRepository.findAll();
    }

    @Override
    public Optional<StatusContract> getById(Long id) {
        return statusContractRepository.findById(id);
    }
}
