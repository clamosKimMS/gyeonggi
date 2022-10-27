package clamos.io.dashboard.service;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolMaxCountDTO;

import java.util.List;

public interface SchoolService {

    List<SchoolDTO> getSchoolCount(String Area);

    Long getAreaSearchCount(String Area);

    // 행정구역별 countList
    List<SchoolMaxCountDTO> getAreaSchoolCountList(String type);
    Long getAreaMaxSchoolTotal(String type);

    // 지역청별 countList
    List<SchoolMaxCountDTO> getEduSchoolCountList(String type);
    Long getEduMaxSchoolTotal(String type);

}