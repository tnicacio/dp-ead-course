package com.ead.course.services.impl;

import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(ModuleModel moduleModel) {
        List<LessonModel> lessonModelList = lessonRepository.findAllByModuleId(moduleModel.getId());
        lessonRepository.deleteAll(lessonModelList);
        moduleRepository.delete(moduleModel);
    }

    @Override
    public ModuleModel save(ModuleModel moduleModule) {
        return moduleRepository.save(moduleModule);
    }

    @Override
    public Optional<ModuleModel> findByIdAndCourseId(UUID moduleId, UUID courseId) {
        return moduleRepository.findByIdAndCourseId(moduleId, courseId);
    }

    @Override
    public List<ModuleModel> findAllByCourseId(UUID courseId) {
        return moduleRepository.findAllByCourseId(courseId);
    }

    @Override
    public Optional<ModuleModel> findById(UUID moduleId) {
        return moduleRepository.findById(moduleId);
    }

}
