package clamos.io.dashboard.studentStatus.repository;

import clamos.io.dashboard.studentStatus.entity.StudentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentStatusRepository extends JpaRepository<StudentStatusEntity, Integer> {

}
