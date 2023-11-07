package com.fastporte.carrierservice.service;

import com.fastporte.carrierservice.entity.Experience;

import java.util.List;

public interface IExperienceService {
    public List<Experience> findByCarrierId(Long carrierId);

    public List<Experience> findExperienceAll();

    public Experience createExperience(Experience experience);

    public Experience updateExperience(Experience experience);

    public Experience deleteExperience(Experience experience);

    public Experience getExperience(Long id);
}
