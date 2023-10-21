package com.personalData.PersonalData.PersonalDataService.service;

import com.personalData.PersonalData.PersonalDataService.entity.ModificationLog;
import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;

import java.util.List;

public interface IModificationLogService {

    List<ModificationLog> findModificationLogAll();

    ModificationLog createModificationLog(ModificationLog modificationLog);

    ModificationLog updateModificationLog(ModificationLog modificationLog);

    ModificationLog deleteModificationLog(ModificationLog modificationLog);

    ModificationLog getModificationLog(Long id);
}
