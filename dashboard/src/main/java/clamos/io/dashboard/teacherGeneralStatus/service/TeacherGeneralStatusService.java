package clamos.io.dashboard.teacherGeneralStatus.service;

import clamos.io.dashboard.teacherGeneralStatus.dto.TeacherGeneralStatusDTO;

import java.util.List;

public interface TeacherGeneralStatusService {

    // 행정구역
    Integer getMaxTeacherArea(String type);
    List<TeacherGeneralStatusDTO> getListTeacherArea(String type);

    // 지역청
    Integer getMaxTeacherEdu(String type);
    List<TeacherGeneralStatusDTO> getListTeacherEdu(String type);

}
