package com.hiring.hiringservice.service.impl;


import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.repository.ContractRepository;
import com.hiring.hiringservice.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    @Transactional // Para que se puedan hacer cambios en la database
    public Contract save(Contract contract) throws Exception {
        return contractRepository.save(contract);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        contractRepository.deleteById(id);
    }

    @Override
    public List<Contract> getAll() throws Exception {
        return contractRepository.findAll();
    }

    @Override
    public Optional<Contract> getById(Long id) throws Exception {
        return contractRepository.findById(id);
    }

    @Override
    public List<Contract> findByDriverId(Long driverId) throws Exception {
        return contractRepository.findByDriverId(driverId);
    }

    @Override
    public List<Contract> findByClientId(Long clientId) throws Exception {
        return contractRepository.findByClientId(clientId);
    }

    @Override
    public List<Contract> findByDriverIdAndClientId(Long driverId, Long clientId) throws Exception {
        return contractRepository.findByDriverIdAndClientId(driverId, clientId);
    }

}
