package clamos.io.dashboard.multiculturalFamilyStudent.service;

import clamos.io.dashboard.multiculturalFamilyStudent.dto.MulticulturalFmDTO;

import java.util.List;

public interface MulticulturalFmService {

    // 행정구역별
    Integer getMaxMultiFmArea(String yr);
    List<MulticulturalFmDTO> getListMultiFmArea(String yr);

    // 지역청별
    Integer getMaxMultiFmEdu(String yr);
    List<MulticulturalFmDTO> getListMultiFmEdu(String yr);

}
