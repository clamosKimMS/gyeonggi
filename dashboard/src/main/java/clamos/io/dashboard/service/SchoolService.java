package clamos.io.dashboard.service;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.entity.SchoolEntity;

import java.util.ArrayList;
import java.util.List;

public interface SchoolService {

    void getSumSchool();

    List<Integer> getYear();
    List<Integer> getYu();
    List<Integer> getEle();
    List<Integer> getMid();
    List<Integer> getHigh();

    default SchoolDTO entityToDto(SchoolEntity entity) {

        SchoolDTO dto = SchoolDTO.builder()
                .idx(entity.getIdx())
                .ctpv(entity.getCtpv())
                .admdst(entity.getAdmdst())
                .schl_type(entity.getSchl_type())
                .survey_base_date(entity.getSurvey_base_date())
                .build();

        return dto;
    }

}
