package com.ead.course.services;

import com.ead.course.models.ModuleModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {

    void delete(ModuleModel moduleModel);

    ModuleModel save(ModuleModel moduleModule);

    Optional<ModuleModel> findByIdAndCourseId(UUID moduleId, UUID courseId);

    List<ModuleModel> findAllByCourseId(UUID courseId);

    Optional<ModuleModel> findById(UUID moduleId);

}