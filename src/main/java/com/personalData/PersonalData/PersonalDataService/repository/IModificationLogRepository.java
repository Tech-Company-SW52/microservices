package com.personalData.PersonalData.PersonalDataService.repository;

import com.personalData.PersonalData.PersonalDataService.entity.ModificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IModificationLogRepository extends JpaRepository<ModificationLog, Long> {

    List<ModificationLog> findByPersonalDataId(Long personalDataId);
}
