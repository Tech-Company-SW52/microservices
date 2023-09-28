package com.hiring.hiringservice.repository;

import com.hiring.hiringservice.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>
{
    List<Contract> findByDriverId(Long driverId);

    List<Contract> findByClientId(Long clientId);

    List<Contract> findByDriverIdAndClientId(Long driverId, Long clientId);

}
