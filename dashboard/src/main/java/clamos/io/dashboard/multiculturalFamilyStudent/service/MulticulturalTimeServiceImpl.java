package clamos.io.dashboard.multiculturalFamilyStudent.service;

import clamos.io.dashboard.multiculturalFamilyStudent.dto.MulticulturalTimeDTO;
import clamos.io.dashboard.multiculturalFamilyStudent.entity.QMulticulturalFamilyEntity;
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
public class MulticulturalTimeServiceImpl implements MulticulturalTimeService {

    @Autowired
    EntityManager em;

    // 행정구역 MAX
    @Override
    public Integer getMaxMultiFmArea(String yr) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMulticulturalFamilyEntity qEntity = QMulticulturalFamilyEntity.multiculturalFamilyEntity;

        Integer queryResult = queryFactory
                .select(qEntity.mc_stdnt_tot.sum())
                .from(qEntity)
                .where(qEntity.yr.like(yr).and(qEntity.admdst.ne("소계")))
                .groupBy(qEntity.admdst)
                .orderBy(qEntity.mc_stdnt_tot.max().desc())
                .limit(1)
                .fetchOne();

        return queryResult;

    }

    // 행정구역별 Max List
    @Override
    public List<MulticulturalTimeDTO> getListMultiFmArea(String yr) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMulticulturalFamilyEntity qEntity = QMulticulturalFamilyEntity.multiculturalFamilyEntity;

        List<MulticulturalTimeDTO> queryResult = queryFactory
                .select(Projections.bean(MulticulturalTimeDTO.class,
                        qEntity.admdst.as("admdst"), qEntity.mc_stdnt_tot.sum().as("mc_stdnt_tot_sum")))
                .from(qEntity)
                .where(qEntity.yr.like(yr)
                        .and(qEntity.admdst.ne("소계")))
                .groupBy(qEntity.admdst)
                .fetch();

        return queryResult;

    }



    // 지역청별
    @Override
    public Integer getMaxMultiFmEdu(String yr) {

        List<MulticulturalTimeDTO> dtoList = getListMultiFmEdu(yr);

        Integer max = 0;

        for (MulticulturalTimeDTO dto : dtoList) {
            max = dto.getMc_stdnt_tot_sum() > max ? dto.getMc_stdnt_tot_sum() : max;
        }

        return max;

    }

    @Override
    public List<MulticulturalTimeDTO> getListMultiFmEdu(String yr) {

        int guri = 0;
        int donducheon = 0;
        int anyang = 0;
        int osan = 0;
        int hanam = 0;
        int uiwang = 0;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMulticulturalFamilyEntity qEntity = QMulticulturalFamilyEntity.multiculturalFamilyEntity;

        List<MulticulturalTimeDTO> queryResult = queryFactory
                .select(Projections.bean(MulticulturalTimeDTO.class,
                        qEntity.admdst.as("admdst"), qEntity.mc_stdnt_tot.sum().as("mc_stdnt_tot_sum")))
                .from(qEntity)
                .where(qEntity.yr.like(yr)
                        .and(qEntity.admdst.ne("소계")))
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
                queryResult.get(i).setMc_stdnt_tot_sum( queryResult.get(i).getMc_stdnt_tot_sum() + queryResult.get(guri).getMc_stdnt_tot_sum() );

            }
            if (queryResult.get(i).getAdmdst().equals("양주시")) {

                queryResult.get(i).setAdmdst("양주시-동두천시");
                queryResult.get(i).setMc_stdnt_tot_sum( queryResult.get(i).getMc_stdnt_tot_sum() + queryResult.get(donducheon).getMc_stdnt_tot_sum() );

            }
            if (queryResult.get(i).getAdmdst().equals("과천시")) {

                queryResult.get(i).setAdmdst("과천시-안양시");
                queryResult.get(i).setMc_stdnt_tot_sum( queryResult.get(i).getMc_stdnt_tot_sum() + queryResult.get(anyang).getMc_stdnt_tot_sum() );

            }
            if (queryResult.get(i).getAdmdst().equals("화성시")) {

                queryResult.get(i).setAdmdst("화성시-오산시");
                queryResult.get(i).setMc_stdnt_tot_sum( queryResult.get(i).getMc_stdnt_tot_sum() + queryResult.get(osan).getMc_stdnt_tot_sum() );

            }
            if (queryResult.get(i).getAdmdst().equals("광주시")) {

                queryResult.get(i).setAdmdst("광주시-하남시");
                queryResult.get(i).setMc_stdnt_tot_sum( queryResult.get(i).getMc_stdnt_tot_sum() + queryResult.get(hanam).getMc_stdnt_tot_sum() );

            }
            if (queryResult.get(i).getAdmdst().equals("군포시")) {

                queryResult.get(i).setAdmdst("군포시-의왕시");
                queryResult.get(i).setMc_stdnt_tot_sum( queryResult.get(i).getMc_stdnt_tot_sum() + queryResult.get(uiwang).getMc_stdnt_tot_sum() );

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



}
