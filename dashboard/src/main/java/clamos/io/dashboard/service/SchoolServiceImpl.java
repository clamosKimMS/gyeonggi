package clamos.io.dashboard.service;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolInterfaceDTO;
import clamos.io.dashboard.dto.SchoolMaxCountDTO;
import clamos.io.dashboard.entity.QSchoolEntity;
import clamos.io.dashboard.repository.SchoolRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService{

    private final SchoolRepository repository;

    @Autowired
    EntityManager em;

    @Override
    public List<SchoolDTO> getSchoolCount(String Area) {

        List<Integer[]> result_tmp = repository.Chart_1(Area);

        List<SchoolDTO> dto = new ArrayList<>();

        for (Integer[] list : result_tmp) {

            SchoolDTO dto_tmp = SchoolDTO.builder()
                    .survey_base_date(list[0])
                    .kinder_cnt(list[1])
                    .ele_cnt(list[2])
                    .mid_cnt(list[3])
                    .high_cnt(list[4])
                    .build();

            dto.add(dto_tmp);
        }

        return dto;

    }

    @Override
    public Long getMaxSchoolTotal() {

        return repository.maxTotal();

    }

    @Override
    public Long getAreaSearchCount(String Area) {
        return repository.areaSearchCount(Area);

    }

    // 지역별 전체 학교 수 ( 학교타입 지정 가능 )
    @Override
    public List<SchoolMaxCountDTO> getSchoolCountList(String type) {

        System.out.println("타입 : "  + type);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchoolEntity qSchoolEntity = QSchoolEntity.schoolEntity;

        List<SchoolMaxCountDTO> queryResult = queryFactory
                .select(Projections.bean(SchoolMaxCountDTO.class,
                        qSchoolEntity.admdst.as("name"),
                        qSchoolEntity.count().as("total_cnt")))
                .from(qSchoolEntity)
                .where(qSchoolEntity.survey_base_date.like("2022")
                        .and(qSchoolEntity.schl_exist_status.notLike("폐(원)교"))
                        .and(qSchoolEntity.main_or_branch_school.notLike("분교장"))
                        .and(eqSchool(type))
                )
                .groupBy(qSchoolEntity.admdst)
                .fetch();


        System.out.println(queryResult);

        return queryResult;

    }

    // 학교 타입
    private BooleanBuilder eqSchool(String type) {

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        QSchoolEntity qSchoolEntity = QSchoolEntity.schoolEntity;

        if (type.contains("k")) {
            conditionBuilder.or(qSchoolEntity.schl_type.contains("유치원"));
        }
        if (type.contains("e")) {
            conditionBuilder.or(qSchoolEntity.schl_type.contains("초등학교"));
        }
        if (type.contains("m")) {
            conditionBuilder.or(qSchoolEntity.schl_type.contains("중학교"));
        }
        if (type.contains("h")) {
            conditionBuilder.or(qSchoolEntity.schl_type.contains("고등학교"));
        }

        return conditionBuilder;

    }

}
