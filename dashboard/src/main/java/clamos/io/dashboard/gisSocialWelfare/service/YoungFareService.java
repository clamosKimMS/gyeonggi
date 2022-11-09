package clamos.io.dashboard.gisSocialWelfare.service;

import clamos.io.dashboard.gisSocialWelfare.dto.YoungFareDto;
import java.util.List;

public interface YoungFareService {

     // 사회복지시설_청소년복지시설_리스트
     List<YoungFareDto> getListYoungFare(String Area);

     // 사회복지시설_청소년복지시설_상세내용
     List<YoungFareDto> getDetailYoungFare();

}
