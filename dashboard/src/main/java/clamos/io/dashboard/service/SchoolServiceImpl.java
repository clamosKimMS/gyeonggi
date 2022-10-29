package clamos.io.dashboard.service;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolInterfaceDTO;
import clamos.io.dashboard.dto.SchoolMaxCountDTO;
import clamos.io.dashboard.entity.QSchoolEntity;
import clamos.io.dashboard.repository.SchoolRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public Long getAreaMaxSchoolTotal(String type) {
        
        // 학교 전체수를 Query DSL로 바꿀 필요 있음

        System.out.println("serviceMax 타입 : " + type);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchoolEntity qSchoolEntity = QSchoolEntity.schoolEntity;

        Long queryResult = queryFactory
                .select(qSchoolEntity.count())
                .from(qSchoolEntity)
                .where(qSchoolEntity.survey_base_date.eq(
                                JPAExpressions
                                        .select(qSchoolEntity.survey_base_date.max())
                                        .from(qSchoolEntity)
                        )
                        .and(qSchoolEntity.schl_exist_status.notLike("폐(원)교"))
                        .and(qSchoolEntity.main_or_branch_school.notLike("분교장"))
                        .and(eqSchool(type)))
                .groupBy(qSchoolEntity.admdst)
                .orderBy(qSchoolEntity.count().desc())
                .limit(1)
                .fetchOne();

        System.out.println(queryResult);

        return queryResult;

    }

    @Override
    public Long getEduMaxSchoolTotal(String type) {

        List<SchoolMaxCountDTO> result = getEduSchoolCountList(type);

        Long max = 0L;

        for (SchoolMaxCountDTO dto : result) {
            log.info(dto);
            max = dto.getTotal_cnt() > max ? max = dto.getTotal_cnt() : max;
        }


        return max;
    }

    @Override
    public Long getAreaSearchCount(String Area) {
        return repository.areaSearchCount(Area);

    }

    // 지역별 전체 학교 수 ( 학교타입 지정 가능 )
    @Override
    public List<SchoolMaxCountDTO> getAreaSchoolCountList(String type) {

        System.out.println("serviceList 타입 : "  + type);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchoolEntity qSchoolEntity = QSchoolEntity.schoolEntity;

        List<SchoolMaxCountDTO> queryResult = queryFactory
                .select(Projections.bean(SchoolMaxCountDTO.class,
                        qSchoolEntity.admdst.as("name"),
                        qSchoolEntity.count().as("total_cnt")))
                .from(qSchoolEntity)
                .where(qSchoolEntity.survey_base_date.eq(
                                        JPAExpressions
                                                .select(qSchoolEntity.survey_base_date.max())
                                                .from(qSchoolEntity)
                                )
                        .and(qSchoolEntity.schl_exist_status.notLike("폐(원)교"))
                        .and(qSchoolEntity.main_or_branch_school.notLike("분교장"))
                        .and(eqSchool(type))
                )
                .groupBy(qSchoolEntity.admdst)
                .fetch();

        System.out.println("지역별 전체 학교 수");
        for (SchoolMaxCountDTO dto : queryResult) {
            System.out.println(dto);
        }

        return queryResult;

    }

    @Override
    public List<SchoolMaxCountDTO> getEduSchoolCountList(String type) {

        int guri = 0;
        int donducheon = 0;
        int anyang = 0;
        int osan = 0;
        int hanam = 0;
        int uiwang = 0;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchoolEntity qSchoolEntity = QSchoolEntity.schoolEntity;

        List<SchoolMaxCountDTO> queryResult = queryFactory
                .select(Projections.bean(SchoolMaxCountDTO.class,
                        qSchoolEntity.admdst.as("name"),
                        qSchoolEntity.count().as("total_cnt")))
                .from(qSchoolEntity)
                .where(qSchoolEntity.survey_base_date.eq(
                                        JPAExpressions
                                                .select(qSchoolEntity.survey_base_date.max())
                                                .from(qSchoolEntity)
                                )
                        .and(qSchoolEntity.schl_exist_status.notLike("폐(원)교"))
                        .and(qSchoolEntity.main_or_branch_school.notLike("분교장"))
                        .and(eqSchool(type))
                )
                .groupBy(qSchoolEntity.admdst)
                .orderBy(qSchoolEntity.admdst.asc())
                .fetch();

        for (int i = 0; i < queryResult.size(); i++) {

            if (queryResult.get(i).getName().equals("구리시")) {
                guri = i;
            }
            if (queryResult.get(i).getName().equals("동두천시")) {
                donducheon = i;
            }
            if (queryResult.get(i).getName().equals("안양시")) {
                anyang = i;
            }
            if (queryResult.get(i).getName().equals("오산시")) {
                osan = i;
            }
            if (queryResult.get(i).getName().equals("하남시")) {
                hanam = i;
            }
            if (queryResult.get(i).getName().equals("의왕시")) {
                uiwang = i;
            }

        }

        for (int i = 0; i < queryResult.size(); i++) {
            if (queryResult.get(i).getName().equals("남양주시")) {

                queryResult.get(i).setName("남양주시-구리시");
                queryResult.get(i).setTotal_cnt( queryResult.get(i).getTotal_cnt() + queryResult.get(guri).getTotal_cnt() );

            }
            if (queryResult.get(i).getName().equals("양주시")) {

                queryResult.get(i).setName("양주시-동두천시");
                queryResult.get(i).setTotal_cnt( queryResult.get(i).getTotal_cnt() + queryResult.get(donducheon).getTotal_cnt() );

            }
            if (queryResult.get(i).getName().equals("과천시")) {

                queryResult.get(i).setName("과천시-안양시");
                queryResult.get(i).setTotal_cnt( queryResult.get(i).getTotal_cnt() + queryResult.get(anyang).getTotal_cnt() );

            }
            if (queryResult.get(i).getName().equals("화성시")) {

                queryResult.get(i).setName("화성시-오산시");
                queryResult.get(i).setTotal_cnt( queryResult.get(i).getTotal_cnt() + queryResult.get(osan).getTotal_cnt() );

            }
            if (queryResult.get(i).getName().equals("광주시")) {

                queryResult.get(i).setName("광주시-하남시");
                queryResult.get(i).setTotal_cnt( queryResult.get(i).getTotal_cnt() + queryResult.get(hanam).getTotal_cnt() );

            }
            if (queryResult.get(i).getName().equals("군포시")) {

                queryResult.get(i).setName("군포시-의왕시");
                queryResult.get(i).setTotal_cnt( queryResult.get(i).getTotal_cnt() + queryResult.get(uiwang).getTotal_cnt() );

            }
        }


        for (int i = 0; i < queryResult.size(); i++) {

            if (queryResult.get(i).getName().equals("구리시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getName().equals("동두천시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getName().equals("안양시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getName().equals("오산시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getName().equals("하남시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getName().equals("의왕시")) {
                queryResult.remove(i);
            }

        }

        return queryResult;

    }

    // 학교 타입
    private BooleanBuilder eqSchool(String type) {

        if (type.equals(null) || type.trim().length() == 0) {

        }

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
