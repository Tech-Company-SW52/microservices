package com.personalData.PersonalData.PersonalDataService.repository;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonalDataRepository extends JpaRepository<PersonalData, Long> {

}
