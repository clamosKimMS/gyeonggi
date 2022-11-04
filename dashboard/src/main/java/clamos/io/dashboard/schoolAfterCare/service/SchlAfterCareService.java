package clamos.io.dashboard.schoolAfterCare.service;

import clamos.io.dashboard.schoolAfterCare.dto.SchlAfterCareDTO;
import java.util.List;

public interface SchlAfterCareService {

    // 행정구역
    List<SchlAfterCareDTO> getLocalSchlAfterCareList(String type);
    Long getLocalSchlAfterCareMax(String type);

    // 지역청
    List<SchlAfterCareDTO> getEduSchlAfterCareList(String type);
    Long getEduSchlAfterCareMax(String type);
}
