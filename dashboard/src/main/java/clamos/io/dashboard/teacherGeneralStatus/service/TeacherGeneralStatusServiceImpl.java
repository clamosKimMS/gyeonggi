package clamos.io.dashboard.teacherGeneralStatus.service;

import clamos.io.dashboard.teacherGeneralStatus.dto.TeacherGeneralStatusDTO;
import clamos.io.dashboard.teacherGeneralStatus.entity.QTeacherGeneralStatusEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherGeneralStatusServiceImpl implements TeacherGeneralStatusService {

    @Autowired
    EntityManager em;

    // 행정구역
    @Override
    public Integer getMaxTeacherMaxArea(String type) {

        List<TeacherGeneralStatusDTO> dtoList = getListTeacherArea(type);

        Integer max = 0;

        for (TeacherGeneralStatusDTO dto : dtoList) {
            max = dto.getTchr_tot_sum() > max ? dto.getTchr_tot_sum() : max;
        }

        return max;
    }

    @Override
    public List<TeacherGeneralStatusDTO> getListTeacherArea(String type) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTeacherGeneralStatusEntity qEntity = QTeacherGeneralStatusEntity.teacherGeneralStatusEntity;

        List<TeacherGeneralStatusDTO> queryResult = queryFactory
                .select(Projections.bean(TeacherGeneralStatusDTO.class,
                        qEntity.admdst.as("admdst"),
                        qEntity.tchr_tot.sum().as("tchr_tot_sum")))
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


    // 지역청
    @Override
    public Integer getMaxTeacherMaxEdu(String type) {

        List<TeacherGeneralStatusDTO> dtoList = getListTeacherEdu(type);

        Integer max = 0;

        for (TeacherGeneralStatusDTO dto : dtoList) {
            max = dto.getTchr_tot_sum() > max ? dto.getTchr_tot_sum() : max;
        }

        return max;

    }

    @Override
    public List<TeacherGeneralStatusDTO> getListTeacherEdu(String type) {

        int guri = 0;
        int donducheon = 0;
        int anyang = 0;
        int osan = 0;
        int hanam = 0;
        int uiwang = 0;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTeacherGeneralStatusEntity qEntity = QTeacherGeneralStatusEntity.teacherGeneralStatusEntity;

        List<TeacherGeneralStatusDTO> queryResult = queryFactory
                .select(Projections.bean(TeacherGeneralStatusDTO.class,
                        qEntity.admdst.as("admdst"),
                        qEntity.tchr_tot.sum().as("tchr_tot_sum")))
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
                queryResult.get(i).setTchr_tot_sum( queryResult.get(i).getTchr_tot_sum() + queryResult.get(guri).getTchr_tot_sum() );

            }
            if (queryResult.get(i).getAdmdst().equals("양주시")) {

                queryResult.get(i).setAdmdst("양주시-동두천시");
                queryResult.get(i).setTchr_tot_sum( queryResult.get(i).getTchr_tot_sum() + queryResult.get(donducheon).getTchr_tot_sum() );

            }
            if (queryResult.get(i).getAdmdst().equals("과천시")) {

                queryResult.get(i).setAdmdst("과천시-안양시");
                queryResult.get(i).setTchr_tot_sum( queryResult.get(i).getTchr_tot_sum() + queryResult.get(anyang).getTchr_tot_sum() );

            }
            if (queryResult.get(i).getAdmdst().equals("화성시")) {

                queryResult.get(i).setAdmdst("화성시-오산시");
                queryResult.get(i).setTchr_tot_sum( queryResult.get(i).getTchr_tot_sum() + queryResult.get(osan).getTchr_tot_sum() );

            }
            if (queryResult.get(i).getAdmdst().equals("광주시")) {

                queryResult.get(i).setAdmdst("광주시-하남시");
                queryResult.get(i).setTchr_tot_sum( queryResult.get(i).getTchr_tot_sum() + queryResult.get(hanam).getTchr_tot_sum() );

            }
            if (queryResult.get(i).getAdmdst().equals("군포시")) {

                queryResult.get(i).setAdmdst("군포시-의왕시");
                queryResult.get(i).setTchr_tot_sum( queryResult.get(i).getTchr_tot_sum() + queryResult.get(uiwang).getTchr_tot_sum() );

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

        /*if (type.equals(null) || type.trim().length() == 0) {

        }*/

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        QTeacherGeneralStatusEntity qEntity = QTeacherGeneralStatusEntity.teacherGeneralStatusEntity;

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
