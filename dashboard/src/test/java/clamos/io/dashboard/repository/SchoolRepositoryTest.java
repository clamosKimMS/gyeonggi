package clamos.io.dashboard.repository;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolInterfaceDTO;
import clamos.io.dashboard.dto.SchoolMaxCountDTO;
import clamos.io.dashboard.entity.QSchoolEntity;
import clamos.io.dashboard.entity.SchoolEntity;
import clamos.io.dashboard.service.SchoolService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class SchoolRepositoryTest {

    @Autowired
    private SchoolRepository repository;

    @Autowired
    private SchoolService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("연결 확인용 테스트")
    public void ConnectionTest() {

        Optional<SchoolEntity> result = repository.findById(100);
        SchoolEntity school = result.get();

        System.out.println(school);

    }

    /*@Test
    @DisplayName("학교타입별 합")
    public void SchoolTypeCountTest() {

        System.out.println(repository.findBySchoolTypeCount());

    }*/

    @Test
    @DisplayName("testData")
    public void testData() {

        List<Integer[]> result_tmp = repository.Chart_1("파주시");

        List<SchoolDTO> result = new ArrayList<>();

        for (Integer[] dto : result_tmp) {

            SchoolDTO school = SchoolDTO.builder()
                    .survey_base_date(dto[0])
                    .kinder_cnt(dto[1])
                    .ele_cnt(dto[2])
                    .mid_cnt(dto[3])
                    .high_cnt(dto[4])
                    .build();
            result.add(school);
        }
        for (SchoolDTO school : result) {
            System.out.println(school.toString());
        }

    }

    @Test
    @DisplayName("Integer[] injection to DTO")
    public void InjectionTest() {

        List<SchoolDTO> dto = service.getSchoolCount("파주시");

        for (SchoolDTO result : dto) {
            System.out.println(result.toString());
        }

    }

    @Test
    @DisplayName("학교 타입별 MAX 단위 테스트")
    public void SchoolTypeTotalTest() {

        Long kinder = repository.maxKinder();
        Long ele = repository.maxEle();
        Long mid = repository.maxMid();
        Long high = repository.maxHigh();

        Long totalMax = repository.maxTotal();

        System.out.println("유치원 맥스   : " + kinder);
        System.out.println("초등학교 맥스 : " + ele);
        System.out.println("중학교 맥스   : " + mid);
        System.out.println("고등학교 맥스 : " + high);
        System.out.println("토탈 맥스 : " + totalMax);

    }

    @Test
    @DisplayName(" 지역 검색 MAX 단위 테스트 ")
    public void SearchPlaceMaxTest() {

        Long currentMaxCount = service.getAreaSearchCount("광주시");
        System.out.println(currentMaxCount);

        assertEquals(5, currentMaxCount);

    }

    @Test
    @DisplayName(" 지역 max count List 단위 테스트 ")
    public void areaSearchCountListTest() {

        List<String[]> result_tmp = repository.areaSearchCountList();
        List<SchoolMaxCountDTO> result = new ArrayList<>();

        for (String[] dto : result_tmp) {
            SchoolMaxCountDTO school = SchoolMaxCountDTO.builder()
                    .name(dto[0])
//                    .total_cnt(Integer.parseInt(dto[1]))
                    .build();

            result.add(school);
        }

        for (SchoolMaxCountDTO dto : result) {
            System.out.println(dto.toString());
        }

    }

    @Test
    @DisplayName("QueryDSL Test")
    public void QueryDSLTest() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchoolEntity qSchoolEntity = QSchoolEntity.schoolEntity;

        List<SchoolMaxCountDTO> query = queryFactory
                .select(Projections.bean(SchoolMaxCountDTO.class, qSchoolEntity.admdst.as("name"), qSchoolEntity.count().as("total_cnt")))
                .from(qSchoolEntity)
                .where(qSchoolEntity.survey_base_date.like("2022")
                        .and(qSchoolEntity.schl_exist_status.notLike("폐(원)교"))
                        .and(qSchoolEntity.main_or_branch_school.notLike("분교장"))
                )
                .groupBy(qSchoolEntity.admdst)
                .fetch();

        System.out.println(query);


        /*JPAQuery<Tuple> query = queryFactory
                .select(qSchoolEntity.admdst, qSchoolEntity.count())
                .from(qSchoolEntity)
                .where(qSchoolEntity.survey_base_date.like("2022")
                        .and(qSchoolEntity.schl_exist_status.notLike("폐(원)교"))
                        .and(qSchoolEntity.main_or_branch_school.notLike("분교장"))
                )
                .groupBy(qSchoolEntity.admdst);

        List<Tuple> itemList = query.fetch();

        for (Tuple tuple : itemList) {
            System.out.println(tuple);
        }*/



    }


    @Test
    @DisplayName("BooleanBuilder")
    public void BooleanBuilderTest() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchoolEntity qSchoolEntity = QSchoolEntity.schoolEntity;

        List<SchoolMaxCountDTO> query = queryFactory
                .select(Projections.bean(SchoolMaxCountDTO.class,
                        qSchoolEntity.admdst.as("name"),
                        qSchoolEntity.count().as("total_cnt")))
                .from(qSchoolEntity)
                .where(qSchoolEntity.survey_base_date.like("2022")
                        .and(qSchoolEntity.schl_exist_status.notLike("폐(원)교"))
                        .and(qSchoolEntity.main_or_branch_school.notLike("분교장"))
                        .and(eqSchool("ke"))
                )
                .groupBy(qSchoolEntity.admdst)
                .fetch();


        System.out.println(query);



    }

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