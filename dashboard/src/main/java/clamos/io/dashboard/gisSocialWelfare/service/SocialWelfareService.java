package clamos.io.dashboard.gisSocialWelfare.service;

import clamos.io.dashboard.gisSocialWelfare.dto.DtoYoungFareDetail;
import clamos.io.dashboard.gisSocialWelfare.dto.DtoYoungFareList;
import java.util.List;

public interface SocialWelfareService {

     // 사회복지시설_청소년복지시설_리스트
     List<DtoYoungFareList> getListYoungFare(String Area, String keyword);

     // 사회복지시설_청소년복지시설_상세내용
     List<DtoYoungFareDetail> getDetailYoungFare();

}
