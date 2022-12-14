package clamos.io.dashboard.schoolGeneralStatus.repository;


import clamos.io.dashboard.schoolGeneralStatus.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Integer>, QuerydslPredicateExecutor<SchoolEntity> {

    @Query(nativeQuery = true,
            value = "select max(cnt) " +
                    "from ( " +
                    " select count(*) as cnt " +
                    " from tb_schl_stat_kms " +
                    " where survey_base_date = (select max(survey_base_date) from tb_schl_stat ) " +
                    " and schl_exist_status <> '폐(원)교' " +
                    " and main_or_branch_school <> '분교장' " +
                    " and admdst = :Area " +
                    "group by admdst ) " +
                    "as query ")
    Long areaSearchCount(@Param("Area") String Area);

    // Chart.js 쓸 때 사용할 것
    @Query(value = "select survey_base_date as survey_base_date" +
            ", coalesce(sum(case when schl_type = '유치원' then schl_count else 0 end), 0) as kinder_cnt " +
            ", coalesce(sum(case when schl_type = '초등학교' then schl_count else 0 end), 0) as ele_cnt " +
            ", coalesce(sum(case when schl_type = '중학교' then schl_count else 0 end), 0) as mid_cnt " +
            ", coalesce(sum(case when schl_type = '고등학교' then schl_count else 0 end), 0) as high_cnt " +
            "from tb_schl_stat_kms " +
            "where schl_exist_status <> '폐(원)교' " +
            "and main_or_branch_school <> '분교장' " +
            "and admdst= :Area " +
            "group by survey_base_date" +
            "", nativeQuery = true)
    List<Integer[]> Chart_1(@Param("Area") String Area);

}
