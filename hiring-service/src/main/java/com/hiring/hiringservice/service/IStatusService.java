package com.hiring.hiringservice.service;

import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.entity.StatusContract;
import org.apache.catalina.LifecycleState;

import java.util.List;
import java.util.Optional;

public interface IStatusService {

    Contract updateStatusMode(Long id, String status);
    StatusContract save(StatusContract statusContract);

    void delete(Long id);

    List<StatusContract> getAll();

    Optional<StatusContract> getById(Long id);
}
