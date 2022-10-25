package clamos.io.dashboard.service;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolInterfaceDTO;
import clamos.io.dashboard.dto.SchoolMaxCountDTO;
import clamos.io.dashboard.entity.SchoolEntity;

import java.util.List;

public interface SchoolService {

    List<SchoolDTO> getSchoolCount(String Area);

    Long getMaxSchoolTotal();

    Long getAreaSearchCount(String Area);

    List<SchoolMaxCountDTO> getSchoolCountList(String type);

}