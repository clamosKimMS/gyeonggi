package clamos.io.dashboard.repository;


import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolInterfaceDTO;
import clamos.io.dashboard.dto.SchoolMaxCountDTO;
import clamos.io.dashboard.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Integer> {

    @Query(nativeQuery = true,
            value = "select max(cnt) " +
                    "from ( " +
                    " select count(*) as cnt " +
                    " from tb_schl_stat_kms " +
                    " where survey_base_date = '2022' " +
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

    @Query(nativeQuery = true,
            value = "select admdst, count(schl_count) " +
                    "from tb_schl_stat_kms " +
                    "where survey_base_date = '2022' " +
                    "and schl_exis" +
                    "t_status <> '폐(원)교' " +
                    "and main_or_branch_school <> '분교장' " +
                    "group by admdst ")
    List<String[]> areaSearchCountList();


    @Query(nativeQuery = true,
            value = "select max(cnt) " +
                    "from ( " +
                    " select count(*) as cnt " +
                    " from tb_schl_stat_kms " +
                    " where survey_base_date = '2022' " +
                    " and schl_exist_status <> '폐(원)교' " +
                    " and main_or_branch_school <> '분교장' " +
                    "group by admdst )" +
                    "as query ")
    Long maxTotal();

}
