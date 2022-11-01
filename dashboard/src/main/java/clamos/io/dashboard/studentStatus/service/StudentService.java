package clamos.io.dashboard.studentStatus.service;

import clamos.io.dashboard.studentStatus.dto.StudentStatusDTO;

import java.util.List;

public interface StudentService {

    // 행정구역별
    Integer getMaxStudentArea(String type);
    List<StudentStatusDTO> getListStudentArea(String type);

    // 지역청별
    Integer getMaxStudentEdu(String type);
    List<StudentStatusDTO> getListStudentEdu(String type);

}
