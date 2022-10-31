package clamos.io.dashboard.multiculturalFamilyStudent.service;

import clamos.io.dashboard.multiculturalFamilyStudent.dto.MulticulturalFamilyDTO;

import java.util.List;

public interface MulticulturalFamilyService {

    // 행정구역별
    Integer getMaxMultiFmArea(String yr);
    List<MulticulturalFamilyDTO> getListMultiFmArea(String yr);

    // 지역청별
    Integer getMaxMultiFmEdu(String yr);
    List<MulticulturalFamilyDTO> getListMultiFmEdu(String yr);

}
