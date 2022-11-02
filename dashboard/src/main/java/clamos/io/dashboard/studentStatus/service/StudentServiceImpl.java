package clamos.io.dashboard.studentStatus.service;

import clamos.io.dashboard.studentStatus.dto.StudentStatusDTO;
import clamos.io.dashboard.studentStatus.entity.QStudentStatusEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    EntityManager em;

    @Override
    public Integer getMaxStudentArea(String type) {

        List<StudentStatusDTO> dtoList = getListStudentArea(type);

        Integer max = 0;

        for (StudentStatusDTO dto : dtoList) {
            max = dto.getStdnt_tot_by_grade_sum() > max ? dto.getStdnt_tot_by_grade_sum() : max;
        }

        return max;
    }

    @Override
    public List<StudentStatusDTO> getListStudentArea(String type) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QStudentStatusEntity qEntity = QStudentStatusEntity.studentStatusEntity;

        List<StudentStatusDTO> queryResult = queryFactory
                .select(Projections.bean(StudentStatusDTO.class,
                        qEntity.admdst.as("admdst"),
                        qEntity.stdnt_tot_by_grade.sum().as("stdnt_tot_by_grade_sum")))
                .from(qEntity)
                .where(qEntity.admdst.isNotNull()
                        .and(qEntity.survey_base_date.eq(
                                JPAExpressions
                                        .select(qEntity.survey_base_date.max())
                                        .from(qEntity)
                        ))
                        .and(eqSchool(type)))
                .groupBy(qEntity.admdst)
                .fetch();

        return queryResult;

    }

    @Override
    public Integer getMaxStudentEdu(String type) {

        List<StudentStatusDTO> dtoList = getListStudentEdu(type);

        Integer max = 0;

        for (StudentStatusDTO dto : dtoList) {
            max = dto.getStdnt_tot_by_grade_sum() > max ? dto.getStdnt_tot_by_grade_sum() : max;
        }

        return max;

    }

    @Override
    public List<StudentStatusDTO> getListStudentEdu(String type) {
        int guri = 0;
        int donducheon = 0;
        int anyang = 0;
        int osan = 0;
        int hanam = 0;
        int uiwang = 0;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QStudentStatusEntity qEntity = QStudentStatusEntity.studentStatusEntity;


        List<StudentStatusDTO> queryResult = queryFactory
                .select(Projections.bean(StudentStatusDTO.class,
                        qEntity.admdst.as("admdst"),
                        qEntity.stdnt_tot_by_grade.sum().as("stdnt_tot_by_grade_sum")))
                .from(qEntity)
                .where(qEntity.admdst.isNotNull()
                        .and(qEntity.survey_base_date.eq(
                                JPAExpressions
                                        .select(qEntity.survey_base_date.max())
                                        .from(qEntity)
                        ))
                        .and(eqSchool(type)))
                .groupBy(qEntity.admdst)
                .fetch();

        for (int i = 0; i < queryResult.size(); i++) {
            if (queryResult.get(i).getAdmdst().equals("구리시")) {
                guri = i;
            }
            if (queryResult.get(i).getAdmdst().equals("동두천시")) {
                donducheon = i;
            }
            if (queryResult.get(i).getAdmdst().equals("안양시")) {
                anyang = i;
            }
            if (queryResult.get(i).getAdmdst().equals("오산시")) {
                osan = i;
            }
            if (queryResult.get(i).getAdmdst().equals("하남시")) {
                hanam = i;
            }
            if (queryResult.get(i).getAdmdst().equals("의왕시")) {
                uiwang = i;
            }
        }

        for (int i = 0; i < queryResult.size(); i++) {
            if (queryResult.get(i).getAdmdst().equals("남양주시")) {

                queryResult.get(i).setAdmdst("남양주시-구리시");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(guri).getStdnt_tot_by_grade_sum());

            }
            if (queryResult.get(i).getAdmdst().equals("양주시")) {

                queryResult.get(i).setAdmdst("양주시-동두천시");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(donducheon).getStdnt_tot_by_grade_sum());

            }
            if (queryResult.get(i).getAdmdst().equals("과천시")) {

                queryResult.get(i).setAdmdst("과천시-안양시");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(anyang).getStdnt_tot_by_grade_sum());

            }
            if (queryResult.get(i).getAdmdst().equals("화성시")) {

                queryResult.get(i).setAdmdst("화성시-오산시");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(osan).getStdnt_tot_by_grade_sum());

            }
            if (queryResult.get(i).getAdmdst().equals("광주시")) {

                queryResult.get(i).setAdmdst("광주시-하남시");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(hanam).getStdnt_tot_by_grade_sum());

            }
            if (queryResult.get(i).getAdmdst().equals("군포시")) {

                queryResult.get(i).setAdmdst("군포시-의왕시");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(uiwang).getStdnt_tot_by_grade_sum());

            }
        }

        for (int i = 0; i < queryResult.size(); i++) {

            if (queryResult.get(i).getAdmdst().equals("구리시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getAdmdst().equals("동두천시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getAdmdst().equals("안양시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getAdmdst().equals("오산시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getAdmdst().equals("하남시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getAdmdst().equals("의왕시")) {
                queryResult.remove(i);
            }

        }

        return queryResult;

    }

    // 학교 타입
    private BooleanBuilder eqSchool(String type) {

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        QStudentStatusEntity qEntity = QStudentStatusEntity.studentStatusEntity;

        if (type.contains("k")) {
            conditionBuilder.or(qEntity.schl_type.contains("유치원"));
        }
        if (type.contains("e")) {
            conditionBuilder.or(qEntity.schl_type.contains("초등학교"));
        }
        if (type.contains("m")) {
            conditionBuilder.or(qEntity.schl_type.contains("중학교"));
        }
        if (type.contains("h")) {
            conditionBuilder.or(qEntity.schl_type.contains("고등학교"));
        }

        return conditionBuilder;

    }

}
