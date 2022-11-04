package clamos.io.dashboard.multiCultureTime.service;

import clamos.io.dashboard.multiCultureTime.dto.MultiCultureTimeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;


public interface MultiCultureTimeService {

    // 행정구역별
    Integer getLocalMultiCultureTimeMax();
    List<MultiCultureTimeDTO> getLocalMultiCultureTimeList();

    // 지역청별
    Integer getEduMultiCultureTimeMax();
    List<MultiCultureTimeDTO> getEduMultiCultureTimeList();

    // 한반도 Max
    /*Integer getKoreaMultiCultureTimeMax();*/

}
