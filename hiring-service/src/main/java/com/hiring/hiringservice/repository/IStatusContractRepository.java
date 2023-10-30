package com.hiring.hiringservice.repository;

import com.hiring.hiringservice.entity.StatusContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusContractRepository extends JpaRepository<StatusContract, Long> {
    StatusContract findByStatus(String status);
}
