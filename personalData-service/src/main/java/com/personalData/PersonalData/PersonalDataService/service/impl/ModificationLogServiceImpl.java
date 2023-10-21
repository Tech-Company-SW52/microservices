package com.personalData.PersonalData.PersonalDataService.service.impl;

import com.personalData.PersonalData.PersonalDataService.entity.ModificationLog;
import com.personalData.PersonalData.PersonalDataService.repository.IModificationLogRepository;
import com.personalData.PersonalData.PersonalDataService.service.IModificationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ModificationLogServiceImpl implements IModificationLogService {

    @Autowired
    IModificationLogRepository modificationLogRepository;

    @Override
    public List<ModificationLog> findModificationLogAll() {
        return modificationLogRepository.findAll();
    }

    @Override
    public ModificationLog createModificationLog(ModificationLog modificationLog) {
        return modificationLogRepository.save(modificationLog);
    }

    @Override
    public ModificationLog updateModificationLog(ModificationLog modificationLog) {
        ModificationLog modificationLogDB = getModificationLog(modificationLog.getId());
        if (modificationLogDB == null) {
            return null;
        }
        return modificationLogRepository.save(modificationLog);
    }

    @Override
    public ModificationLog deleteModificationLog(ModificationLog modificationLog) {
        ModificationLog modificationLogDB = getModificationLog(modificationLog.getId());
        if (modificationLogDB == null) {
            return null;
        }
        modificationLogRepository.deleteById(modificationLog.getId());
        return modificationLogDB;
    }

    @Override
    public ModificationLog getModificationLog(Long id) {
        return modificationLogRepository.findById(id).orElse(null);
    }
}
