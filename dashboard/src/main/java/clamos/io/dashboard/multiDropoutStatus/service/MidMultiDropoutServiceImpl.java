package clamos.io.dashboard.multiDropoutStatus.service;

import clamos.io.dashboard.multiDropoutStatus.dto.HighMultiDropoutDTO;
import clamos.io.dashboard.multiDropoutStatus.dto.MidMultiDropoutDTO;
import clamos.io.dashboard.multiDropoutStatus.entity.QHighMultiDropoutEntity;
import clamos.io.dashboard.multiDropoutStatus.entity.QMidMultiDropoutEntity;
import com.querydsl.core.types.Projections;
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
public class MidMultiDropoutServiceImpl implements MidMultiDropoutService {

    private final EntityManager em;

    @Override
    public List<MidMultiDropoutDTO> getLocalMidDropoutList(String year) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMidMultiDropoutEntity qEntity = QMidMultiDropoutEntity.midMultiDropoutEntity;

        List<MidMultiDropoutDTO> queryResult = queryFactory
                .select(Projections.bean(MidMultiDropoutDTO.class,
                        qEntity.site_nm.as("site_nm"), qEntity.ppltn_sum.sum().as("ppltn_sum_total")))
                .from(qEntity)
                .where(qEntity.yr.like(year))
                .groupBy(qEntity.site_nm)
                .fetch();

        return queryResult;

    }

    @Override
    public List<MidMultiDropoutDTO> getEduMidDropoutList(String year) {

        int guri = 0;
        int donducheon = 0;
        int anyang = 0;
        int osan = 0;
        int hanam = 0;
        int uiwang = 0;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMidMultiDropoutEntity qEntity = QMidMultiDropoutEntity.midMultiDropoutEntity;

        List<MidMultiDropoutDTO> queryResult = queryFactory
                .select(Projections.bean(MidMultiDropoutDTO.class,
                        qEntity.site_nm.as("site_nm"), qEntity.ppltn_sum.sum().as("ppltn_sum_total")))
                .from(qEntity)
                .where(qEntity.yr.like(year))
                .groupBy(qEntity.site_nm)
                .fetch();

        for (int i = 0; i < queryResult.size(); i++) {
            if (queryResult.get(i).getSite_nm().equals("?????????")) {
                guri = i;
            }
            if (queryResult.get(i).getSite_nm().equals("????????????")) {
                donducheon = i;
            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {
                anyang = i;
            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {
                osan = i;
            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {
                hanam = i;
            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {
                uiwang = i;
            }
        }

        for (int i = 0; i < queryResult.size(); i++) {
            if (queryResult.get(i).getSite_nm().equals("????????????")) {

                queryResult.get(i).setSite_nm("????????????-?????????");
                queryResult.get(i).setPpltn_sum_total(queryResult.get(i).getPpltn_sum_total() + queryResult.get(guri).getPpltn_sum_total());

            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {

                queryResult.get(i).setSite_nm("?????????-????????????");
                queryResult.get(i).setPpltn_sum_total(queryResult.get(i).getPpltn_sum_total() + queryResult.get(donducheon).getPpltn_sum_total());

            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {

                queryResult.get(i).setSite_nm("?????????-?????????");
                queryResult.get(i).setPpltn_sum_total(queryResult.get(i).getPpltn_sum_total() + queryResult.get(anyang).getPpltn_sum_total());

            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {

                queryResult.get(i).setSite_nm("?????????-?????????");
                queryResult.get(i).setPpltn_sum_total(queryResult.get(i).getPpltn_sum_total() + queryResult.get(osan).getPpltn_sum_total());

            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {

                queryResult.get(i).setSite_nm("?????????-?????????");
                queryResult.get(i).setPpltn_sum_total(queryResult.get(i).getPpltn_sum_total() + queryResult.get(hanam).getPpltn_sum_total());

            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {

                queryResult.get(i).setSite_nm("?????????-?????????");
                queryResult.get(i).setPpltn_sum_total(queryResult.get(i).getPpltn_sum_total() + queryResult.get(uiwang).getPpltn_sum_total());

            }
        }

        for (int i = 0; i < queryResult.size(); i++) {

            if (queryResult.get(i).getSite_nm().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSite_nm().equals("????????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSite_nm().equals("?????????")) {
                queryResult.remove(i);
            }

        }

        return queryResult;
    }

}
