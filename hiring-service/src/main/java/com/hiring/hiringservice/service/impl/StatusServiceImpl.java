package com.hiring.hiringservice.service.impl;

import com.hiring.hiringservice.entity.StatusContract;
import com.hiring.hiringservice.repository.StatusRepository;
import com.hiring.hiringservice.service.StatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // Solo servicios de lectura
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusContractRepository) {
        this.statusRepository = statusContractRepository;
    }

    @Override
    public StatusContract save(StatusContract statusContract) {
        return statusRepository.save(statusContract);
    }

    @Override
    public void delete(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public List<StatusContract> getAll() throws Exception {
        return statusRepository.findAll();
    }

    @Override
    public Optional<StatusContract> getById(Long id) throws Exception {
        return statusRepository.findById(id);
    }
}
