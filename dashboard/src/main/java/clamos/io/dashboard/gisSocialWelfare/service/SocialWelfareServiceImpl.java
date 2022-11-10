package clamos.io.dashboard.gisSocialWelfare.service;


import clamos.io.dashboard.gisSocialWelfare.dto.DtoYoungFareDetail;
import clamos.io.dashboard.gisSocialWelfare.dto.DtoYoungFareList;
import clamos.io.dashboard.gisSocialWelfare.entity.QYoungFareEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class SocialWelfareServiceImpl implements SocialWelfareService{

     private final EntityManager em;

     // 사회복지시설_청소년복지시설_리스트
     @Override
     public List<DtoYoungFareList> getListYoungFare(String Area, String keyword) {

          JPAQueryFactory queryFactory = new JPAQueryFactory(em);
          QYoungFareEntity qYoungFareEntity = QYoungFareEntity.youngFareEntity;

          return null;

     }

     // 사회복지시설_청소년복지시설_상세내용
     @Override
     public List<DtoYoungFareDetail> getDetailYoungFare() {
          return null;
     }
}
