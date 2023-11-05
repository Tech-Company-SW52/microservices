package com.fastporte.carrierservice.service.impl;

import com.fastporte.carrierservice.entity.Experience;
import com.fastporte.carrierservice.repository.IExperienceRepository;
import com.fastporte.carrierservice.service.IExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements IExperienceService {

    @Autowired
    IExperienceRepository experienceRepository;

    @Override
    public List<Experience> findByCarrierId(Long carrierId) {
        return experienceRepository.findByCarrierId(carrierId);
    }

    @Override
    public List<Experience> findExperienceAll() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience createExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public Experience updateExperience(Experience experience) {
        Experience experienceDB = getExperience(experience.getId());
        if (experienceDB == null) {
            return null;
        }
        return experienceRepository.save(experience);
    }

    @Override
    public Experience deleteExperience(Experience experience) {
        Experience experienceDB = getExperience(experience.getId());
        if (experienceDB == null) {
            return null;
        }
        experienceRepository.deleteById(experience.getId());
        return experienceDB;
    }

    @Override
    public Experience getExperience(Long id) {
        return experienceRepository.findById(id).orElse(null);
    }
}
