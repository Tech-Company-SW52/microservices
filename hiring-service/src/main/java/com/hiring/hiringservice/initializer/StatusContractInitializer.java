package com.hiring.hiringservice.initializer;

import com.hiring.hiringservice.entity.StatusContract;
import com.hiring.hiringservice.repository.IStatusContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusContractInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IStatusContractRepository statusContractRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (statusContractRepository.count() == 0) {
            List<StatusContract> statusContractList = List.of(
                    new StatusContract(0L, "OFFER"),
                    new StatusContract(0L, "PENDING"),
                    new StatusContract(0L,"HISTORY")
            );

            statusContractRepository.saveAll(statusContractList);
        }
    }
}
