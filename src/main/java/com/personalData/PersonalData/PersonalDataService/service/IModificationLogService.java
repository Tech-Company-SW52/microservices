package com.personalData.PersonalData.PersonalDataService.service;

import com.personalData.PersonalData.PersonalDataService.entity.ModificationLog;
import java.util.List;

public interface IModificationLogService {

    List<ModificationLog> findAll();

    ModificationLog get(Long id);

    ModificationLog create(ModificationLog modificationLog);

    void delete(ModificationLog modificationLog);

    List<ModificationLog> findByPersonalDataId(Long personalDataId);
}
