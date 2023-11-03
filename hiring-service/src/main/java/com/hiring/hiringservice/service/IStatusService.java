package com.hiring.hiringservice.service;

import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.entity.StatusContract;

public interface IStatusService {

    Contract updateStatusMode(Long id, String status);
}
