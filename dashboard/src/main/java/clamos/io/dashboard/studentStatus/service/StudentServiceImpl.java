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

    private final EntityManager em;

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
            if (queryResult.get(i).getAdmdst().equals("?????????")) {
                guri = i;
            }
            if (queryResult.get(i).getAdmdst().equals("????????????")) {
                donducheon = i;
            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {
                anyang = i;
            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {
                osan = i;
            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {
                hanam = i;
            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {
                uiwang = i;
            }
        }

        for (int i = 0; i < queryResult.size(); i++) {
            if (queryResult.get(i).getAdmdst().equals("????????????")) {

                queryResult.get(i).setAdmdst("????????????-?????????");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(guri).getStdnt_tot_by_grade_sum());

            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {

                queryResult.get(i).setAdmdst("?????????-????????????");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(donducheon).getStdnt_tot_by_grade_sum());

            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {

                queryResult.get(i).setAdmdst("?????????-?????????");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(anyang).getStdnt_tot_by_grade_sum());

            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {

                queryResult.get(i).setAdmdst("?????????-?????????");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(osan).getStdnt_tot_by_grade_sum());

            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {

                queryResult.get(i).setAdmdst("?????????-?????????");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(hanam).getStdnt_tot_by_grade_sum());

            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {

                queryResult.get(i).setAdmdst("?????????-?????????");
                queryResult.get(i).setStdnt_tot_by_grade_sum(queryResult.get(i).getStdnt_tot_by_grade_sum() + queryResult.get(uiwang).getStdnt_tot_by_grade_sum());

            }
        }

        for (int i = 0; i < queryResult.size(); i++) {

            if (queryResult.get(i).getAdmdst().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getAdmdst().equals("????????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getAdmdst().equals("?????????")) {
                queryResult.remove(i);
            }

        }

        return queryResult;

    }

    // ?????? ??????
    private BooleanBuilder eqSchool(String type) {

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        QStudentStatusEntity qEntity = QStudentStatusEntity.studentStatusEntity;

        if (type.contains("k")) {
            conditionBuilder.or(qEntity.schl_type.contains("?????????"));
        }
        if (type.contains("e")) {
            conditionBuilder.or(qEntity.schl_type.contains("????????????"));
        }
        if (type.contains("m")) {
            conditionBuilder.or(qEntity.schl_type.contains("?????????"));
        }
        if (type.contains("h")) {
            conditionBuilder.or(qEntity.schl_type.contains("????????????"));
        }

        return conditionBuilder;

    }

}
