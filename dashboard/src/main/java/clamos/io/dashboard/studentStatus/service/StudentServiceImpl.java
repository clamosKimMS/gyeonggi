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
                .fetch();

        return queryResult;

    }

    @Override
    public Integer getMaxStudentEdu(String type) {
        return null;
    }

    @Override
    public List<StudentStatusDTO> getListStudentEdu(String type) {
        
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
