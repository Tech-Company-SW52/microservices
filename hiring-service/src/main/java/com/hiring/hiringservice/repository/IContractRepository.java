package com.hiring.hiringservice.repository;

import com.hiring.hiringservice.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByStatusStatusAndCarrierId(String status, Long id);

    List<Contract> findByCarrierId(Long id);
}
