package clamos.io.dashboard.schoolAfterCare.service;

import clamos.io.dashboard.schoolAfterCare.dto.SchlAfterCareDTO;
import clamos.io.dashboard.schoolAfterCare.entity.QSchlAfterCareEntity;
import clamos.io.dashboard.teacherGeneralStatus.entity.QTeacherGeneralStatusEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class SchlAfterCareServiceImpl implements SchlAfterCareService {


    private final EntityManager em;

    @Override
    public List<SchlAfterCareDTO> getLocalSchlAfterCareList(String type) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchlAfterCareEntity qEntity = QSchlAfterCareEntity.schlAfterCareEntity;

        List<SchlAfterCareDTO> queryResult = queryFactory
            .select(Projections.bean(SchlAfterCareDTO.class,
                qEntity.schl_stat_regional_area.as("schl_stat_regional_area"),
                qEntity.aftr_schl_care_oprtn_yn.count().as("aftr_schl_care_oprtn_yn_count")))
            .from(qEntity)
            .where(eqSchool(type)
                .and(qEntity.yr.eq(
                    JPAExpressions
                        .select(qEntity.yr.max())
                        .from(qEntity)
                )))
            .groupBy(qEntity.schl_stat_regional_area)
            .fetch();

        return queryResult;
    }

    @Override
    public Long getLocalSchlAfterCareMax(String type) {

        List<SchlAfterCareDTO> dtoList = getLocalSchlAfterCareList(type);

        Long max = 0L;

        for (SchlAfterCareDTO dto : dtoList) {
            max = dto.getAftr_schl_care_oprtn_yn_count() > max ? dto.getAftr_schl_care_oprtn_yn_count() : max;
        }

        return max;
    }

    @Override
    public List<SchlAfterCareDTO> getEduSchlAfterCareList(String type) {

        int guri = 0;
        int donducheon = 0;
        int anyang = 0;
        int osan = 0;
        int hanam = 0;
        int uiwang = 0;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchlAfterCareEntity qEntity = QSchlAfterCareEntity.schlAfterCareEntity;

        List<SchlAfterCareDTO> queryResult = queryFactory
            .select(Projections.bean(SchlAfterCareDTO.class,
                qEntity.schl_stat_regional_area.as("schl_stat_regional_area"),
                qEntity.aftr_schl_care_oprtn_yn.count().as("aftr_schl_care_oprtn_yn_count")))
            .from(qEntity)
            .where(eqSchool(type)
                .and(qEntity.yr.eq(
                    JPAExpressions
                        .select(qEntity.yr.max())
                        .from(qEntity)
                )))
            .groupBy(qEntity.schl_stat_regional_area)
            .fetch();

        for (int i = 0; i < queryResult.size(); i++) {
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {
                guri = i;
            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("????????????")) {
                donducheon = i;
            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {
                anyang = i;
            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {
                osan = i;
            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {
                hanam = i;
            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {
                uiwang = i;
            }
        }

        for (int i = 0; i < queryResult.size(); i++) {
            if (queryResult.get(i).getSchl_stat_regional_area().equals("????????????")) {

                queryResult.get(i).setSchl_stat_regional_area("????????????-?????????");
                queryResult.get(i).setAftr_schl_care_oprtn_yn_count( queryResult.get(i).getAftr_schl_care_oprtn_yn_count() + queryResult.get(guri).getAftr_schl_care_oprtn_yn_count() );

            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {

                queryResult.get(i).setSchl_stat_regional_area("?????????-????????????");
                queryResult.get(i).setAftr_schl_care_oprtn_yn_count( queryResult.get(i).getAftr_schl_care_oprtn_yn_count() + queryResult.get(donducheon).getAftr_schl_care_oprtn_yn_count() );

            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {

                queryResult.get(i).setSchl_stat_regional_area("?????????-?????????");
                queryResult.get(i).setAftr_schl_care_oprtn_yn_count( queryResult.get(i).getAftr_schl_care_oprtn_yn_count() + queryResult.get(anyang).getAftr_schl_care_oprtn_yn_count() );

            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {

                queryResult.get(i).setSchl_stat_regional_area("?????????-?????????");
                queryResult.get(i).setAftr_schl_care_oprtn_yn_count( queryResult.get(i).getAftr_schl_care_oprtn_yn_count() + queryResult.get(osan).getAftr_schl_care_oprtn_yn_count() );

            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {

                queryResult.get(i).setSchl_stat_regional_area("?????????-?????????");
                queryResult.get(i).setAftr_schl_care_oprtn_yn_count( queryResult.get(i).getAftr_schl_care_oprtn_yn_count() + queryResult.get(hanam).getAftr_schl_care_oprtn_yn_count() );

            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {

                queryResult.get(i).setSchl_stat_regional_area("?????????-?????????");
                queryResult.get(i).setAftr_schl_care_oprtn_yn_count( queryResult.get(i).getAftr_schl_care_oprtn_yn_count() + queryResult.get(uiwang).getAftr_schl_care_oprtn_yn_count() );

            }
        }

        for (int i = 0; i < queryResult.size(); i++) {

            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("????????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSchl_stat_regional_area().equals("?????????")) {
                queryResult.remove(i);
            }

        }

        return queryResult;

    }

    @Override
    public Long getEduSchlAfterCareMax(String type) {

        List<SchlAfterCareDTO> dtoList = getEduSchlAfterCareList(type);

        Long max = 0L;

        for (SchlAfterCareDTO dto : dtoList) {
            max = dto.getAftr_schl_care_oprtn_yn_count() > max ? dto.getAftr_schl_care_oprtn_yn_count() : max;
        }

        return max;
    }


    // ?????? ??????
    private BooleanBuilder eqSchool(String type) {

        /*if (type.equals(null) || type.trim().length() == 0) {

        }*/

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        QSchlAfterCareEntity qEntity = QSchlAfterCareEntity.schlAfterCareEntity;

        if (type.contains("e")) {
            conditionBuilder.or(qEntity.schl_stat_schl_type.contains("???"));
        }
        if (type.contains("m")) {
            conditionBuilder.or(qEntity.schl_stat_schl_type.contains("???"));
        }
        if (type.contains("h")) {
            conditionBuilder.or(qEntity.schl_stat_schl_type.contains("???"));
        }

        return conditionBuilder;

    }

}

