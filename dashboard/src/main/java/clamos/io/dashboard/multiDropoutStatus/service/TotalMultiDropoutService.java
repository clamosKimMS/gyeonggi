package clamos.io.dashboard.multiDropoutStatus.service;


import clamos.io.dashboard.multiDropoutStatus.dto.TotalMultiDropoutDTO;

import java.util.List;

public interface TotalMultiDropoutService {

    // 행정구역
    List<TotalMultiDropoutDTO> getLocalTotalDropoutList(String year);
    Integer getLocalTotalDropoutMax(String year);

    // 지역청
    List<TotalMultiDropoutDTO> getEduTotalDropoutList(String year);
    Integer getEduTotalDropoutMax(String year);
}
