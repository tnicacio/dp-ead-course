package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID>, JpaSpecificationExecutor<ModuleModel> {
    
//    @EntityGraph(attributePaths = {"course"})
//    ModuleModel findByTitle(String title);

//    @Query(value = "select * from tb_modules where course_id = :courseId", nativeQuery = true)
//    List<ModuleModel> findAllModulesIntoCourses(@Param("courseId") UUID courseId);

    List<ModuleModel> findAllByCourseId(UUID courseId);

    Optional<ModuleModel> findByIdAndCourseId(UUID moduleId, UUID courseId);
}
