package clamos.io.dashboard.multiCultureTime.service;

import clamos.io.dashboard.multiCultureTime.dto.MultiCultureTimeDTO;
import clamos.io.dashboard.multiCultureTime.entity.QMultiCultureTimeEntity;
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
public class MultiCultureTimeServiceImpl implements MultiCultureTimeService {

    private final EntityManager em;

    @Override
    public Integer getLocalMultiCultureTimeMax() {

        List<MultiCultureTimeDTO> dtoList = getLocalMultiCultureTimeList();

        Integer max = 0;

        for (MultiCultureTimeDTO dto : dtoList) {
            max = dto.getMulti_culture_stu_cnt_sum() > max ? dto.getMulti_culture_stu_cnt_sum() : max;
        }

        return max;
    }

    @Override
    public List<MultiCultureTimeDTO> getLocalMultiCultureTimeList() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMultiCultureTimeEntity qEntity = QMultiCultureTimeEntity.multiCultureTimeEntity;

        List<MultiCultureTimeDTO> queryResult = queryFactory
                .select(Projections.bean(MultiCultureTimeDTO.class,
                        qEntity.sigun.as("sigun"), qEntity.multi_culture_stu_cnt.sum().as("multi_culture_stu_cnt_sum")))
                .from(qEntity)
                .where(qEntity.ctpv.eq("경기")
                        .and(qEntity.yr.eq(
                                JPAExpressions
                                        .select(qEntity.yr.max())
                                        .from(qEntity)
                        )))
                .groupBy(qEntity.sigun)
                .fetch();

        return queryResult;
    }

    @Override
    public Integer getEduMultiCultureTimeMax() {

        List<MultiCultureTimeDTO> dtoList = getEduMultiCultureTimeList();

        Integer max = 0;

        for (MultiCultureTimeDTO dto : dtoList) {
            max = dto.getMulti_culture_stu_cnt_sum() > max ? dto.getMulti_culture_stu_cnt_sum() : max;
        }

        return max;
    }

    @Override
    public List<MultiCultureTimeDTO> getEduMultiCultureTimeList() {

        int guri = 0;
        int donducheon = 0;
        int anyang = 0;
        int osan = 0;
        int hanam = 0;
        int uiwang = 0;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMultiCultureTimeEntity qEntity = QMultiCultureTimeEntity.multiCultureTimeEntity;

        List<MultiCultureTimeDTO> queryResult = queryFactory
                .select(Projections.bean(MultiCultureTimeDTO.class,
                        qEntity.sigun.as("sigun"), qEntity.multi_culture_stu_cnt.sum().as("multi_culture_stu_cnt_sum")))
                .from(qEntity)
                .where(qEntity.ctpv.eq("경기")
                        .and(qEntity.yr.eq(
                                JPAExpressions
                                        .select(qEntity.yr.max())
                                        .from(qEntity)
                        )))
                .groupBy(qEntity.sigun)
                .fetch();

        for (int i = 0; i < queryResult.size(); i++) {

            if (queryResult.get(i).getSigun().equals("구리시")) {
                guri = i;
            }
            if (queryResult.get(i).getSigun().equals("동두천시")) {
                donducheon = i;
            }
            if (queryResult.get(i).getSigun().equals("안양시")) {
                anyang = i;
            }
            if (queryResult.get(i).getSigun().equals("오산시")) {
                osan = i;
            }
            if (queryResult.get(i).getSigun().equals("하남시")) {
                hanam = i;
            }
            if (queryResult.get(i).getSigun().equals("의왕시")) {
                uiwang = i;
            }

        }

        for (int i = 0; i < queryResult.size(); i++) {
            if (queryResult.get(i).getSigun().equals("남양주시")) {

                queryResult.get(i).setSigun("남양주시-구리시");
                queryResult.get(i).setMulti_culture_stu_cnt_sum(queryResult.get(i).getMulti_culture_stu_cnt_sum() + queryResult.get(guri).getMulti_culture_stu_cnt_sum());

            }
            if (queryResult.get(i).getSigun().equals("양주시")) {

                queryResult.get(i).setSigun("양주시-동두천시");
                queryResult.get(i).setMulti_culture_stu_cnt_sum(queryResult.get(i).getMulti_culture_stu_cnt_sum() + queryResult.get(donducheon).getMulti_culture_stu_cnt_sum());

            }
            if (queryResult.get(i).getSigun().equals("과천시")) {

                queryResult.get(i).setSigun("과천시-안양시");
                queryResult.get(i).setMulti_culture_stu_cnt_sum(queryResult.get(i).getMulti_culture_stu_cnt_sum() + queryResult.get(anyang).getMulti_culture_stu_cnt_sum());

            }
            if (queryResult.get(i).getSigun().equals("화성시")) {

                queryResult.get(i).setSigun("화성시-오산시");
                queryResult.get(i).setMulti_culture_stu_cnt_sum(queryResult.get(i).getMulti_culture_stu_cnt_sum() + queryResult.get(osan).getMulti_culture_stu_cnt_sum());

            }
            if (queryResult.get(i).getSigun().equals("광주시")) {

                queryResult.get(i).setSigun("광주시-하남시");
                queryResult.get(i).setMulti_culture_stu_cnt_sum(queryResult.get(i).getMulti_culture_stu_cnt_sum() + queryResult.get(hanam).getMulti_culture_stu_cnt_sum());

            }
            if (queryResult.get(i).getSigun().equals("군포시")) {

                queryResult.get(i).setSigun("군포시-의왕시");
                queryResult.get(i).setMulti_culture_stu_cnt_sum(queryResult.get(i).getMulti_culture_stu_cnt_sum() + queryResult.get(uiwang).getMulti_culture_stu_cnt_sum());

            }
        }

        for (int i = 0; i < queryResult.size(); i++) {

            if (queryResult.get(i).getSigun().equals("구리시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSigun().equals("동두천시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSigun().equals("안양시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSigun().equals("오산시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSigun().equals("하남시")) {
                queryResult.remove(i);
            }
            if (queryResult.get(i).getSigun().equals("의왕시")) {
                queryResult.remove(i);
            }
        }

        return queryResult;

    }

    /*@Override
    public Integer getKoreaMultiCultureTimeMax() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMultiCultureTimeEntity qEntity = QMultiCultureTimeEntity.multiCultureTimeEntity;

        List<MultiCultureTimeDTO> queryResult = queryFactory
                .select(Projections.bean(MultiCultureTimeDTO.class,
                        qEntity.sigun.as("sigun"), qEntity.multi_culture_stu_cnt.sum().as("multi_culture_stu_cnt_sum")))
                .from(qEntity)
                .where(qEntity.yr.eq(
                        JPAExpressions
                                .select(qEntity.yr.max())
                                .from(qEntity)
                ))
                .groupBy(qEntity.sigun)
                .fetch();

        Integer max = 0;

        for (MultiCultureTimeDTO dto : queryResult) {
            max = dto.getMulti_culture_stu_cnt_sum() > max ? dto.getMulti_culture_stu_cnt_sum() : max;
        }

        return max;

    }*/
}
