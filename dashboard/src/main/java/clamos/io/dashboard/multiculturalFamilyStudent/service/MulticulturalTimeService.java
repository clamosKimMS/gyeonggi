package clamos.io.dashboard.multiculturalFamilyStudent.service;

import clamos.io.dashboard.multiculturalFamilyStudent.dto.MulticulturalTimeDTO;

import java.util.List;

public interface MulticulturalTimeService {

    // 행정구역별
    Integer getMaxMultiFmArea(String yr);
    List<MulticulturalTimeDTO> getListMultiFmArea(String yr);

    // 지역청별
    Integer getMaxMultiFmEdu(String yr);
    List<MulticulturalTimeDTO> getListMultiFmEdu(String yr);

}
