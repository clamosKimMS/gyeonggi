package clamos.io.dashboard.teacherGeneralStatus.repository;

import clamos.io.dashboard.teacherGeneralStatus.entity.TeacherGeneralStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherGeneralStatusRepository extends JpaRepository<TeacherGeneralStatusEntity, Integer> {
}
