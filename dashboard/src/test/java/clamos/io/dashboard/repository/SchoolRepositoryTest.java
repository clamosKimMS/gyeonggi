package clamos.io.dashboard.repository;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolMaxCountDTO;
import clamos.io.dashboard.entity.QSchoolEntity;
import clamos.io.dashboard.entity.SchoolEntity;
import clamos.io.dashboard.service.SchoolService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLTemplates;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
    @DisplayName(" 지역 검색 MAX 단위 테스트 ")
    public void SearchPlaceMaxTest() {

        Long currentMaxCount = service.getAreaSearchCount("광주시");
        System.out.println(currentMaxCount);

        assertEquals(5, currentMaxCount);

    }

    /*@Test
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

    }*/

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


        System.out.println("dtoList - ");
        for (SchoolMaxCountDTO dto : query) {
            System.out.println(dto);
        }

    }

    @Test
    @DisplayName("QueryDSL 전체 학교 수 테스트")
    public void getMaxSchoolTotalTest() {

        String type = "ke";

        System.out.println("serviceMax 타입 : " + type);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchoolEntity qSchoolEntity = QSchoolEntity.schoolEntity;

        Long queryResult = queryFactory
                .select(qSchoolEntity.count())
                .from(qSchoolEntity)
                .where(qSchoolEntity.survey_base_date.like("2022")
                        .and(qSchoolEntity.schl_exist_status.notLike("폐(원)교"))
                        .and(qSchoolEntity.main_or_branch_school.notLike("분교장"))
                        .and(eqSchool(type)))
                .groupBy(qSchoolEntity.admdst)
                .orderBy(qSchoolEntity.count().desc())
                .limit(1)
                .fetchOne();

        System.out.println(queryResult);

    }

    @Test
    @DisplayName("행정구역 / 지역청 구분")
    public void ClassificationTest() {

        // given
        String type = "kemh"; // 유초중고 전체 선택

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
                .orderBy(qSchoolEntity.admdst.asc())
                .fetch();

        System.out.println("가공전 dto - ");
        for (SchoolMaxCountDTO dto : queryResult) {
            System.out.println(dto);
        }

        // when
        System.out.println(" \n\n ============== when 절 ============== ");


        int NamYangju = 0;
        int Yangju = 0;
        int Gwacheon = 0;
        int Hwaseon = 0;
        int Gwangju = 0;
        int Gunpo = 0;

        for (int i = 0; i < queryResult.size(); i++) {
            if (queryResult.get(i).getName().equals("남양주시")) {
                NamYangju = i;
            }
            if (queryResult.get(i).getName().equals("양주시")) {
                Yangju = i;
            }
            if (queryResult.get(i).getName().equals("과천시")) {
                Gwacheon = i;
            }
            if (queryResult.get(i).getName().equals("화성시")) {
                Hwaseon = i;
            }
            if (queryResult.get(i).getName().equals("광주시")) {
                Gwangju = i;
            }
            if (queryResult.get(i).getName().equals("군포시")) {
                Gunpo = i;
            }
        }

        System.out.println(NamYangju + " " + Yangju + " " + Gwacheon + " " + Hwaseon + " " + Gwangju + " " + Gunpo);

        for (int i = 0; i < queryResult.size(); i++) {

            if (queryResult.get(i).getName().equals("구리시")) {

            }
            if (queryResult.get(i).getName().equals("동두천시")) {

            }
            if (queryResult.get(i).getName().equals("안양시")) {

            }
            if (queryResult.get(i).getName().equals("오산시")) {

            }
            if (queryResult.get(i).getName().equals("하남시")) {

            }
            if (queryResult.get(i).getName().equals("의왕시")) {

            }
        }


        // then


    }

    @Test
    @DisplayName("JPASQLQueryTest")
    public void JPASQLQueryTest() {

        String type = "ke";

        QSchoolEntity qSchoolEntity = QSchoolEntity.schoolEntity;

        StringExpression cases = new CaseBuilder()
                .when(qSchoolEntity.admdst.eq("구리시")).then("구리시-남양주시")
                .when(qSchoolEntity.admdst.eq("남양주시")).then("구리시-남양주시")
                .otherwise(Expressions.nullExpression());

        JPASQLQuery<QSchoolEntity> query = new JPASQLQuery<>(em, SQLTemplates.DEFAULT);

        List<SchoolMaxCountDTO> list = query
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
                .orderBy(qSchoolEntity.admdst.asc())
                .fetch();

        for (SchoolMaxCountDTO dto : list) {
            System.out.println(dto);
        }

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