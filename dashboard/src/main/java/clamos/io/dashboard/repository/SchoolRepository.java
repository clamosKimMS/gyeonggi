package clamos.io.dashboard.repository;


import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolInterfaceDTO;
import clamos.io.dashboard.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Integer> {

    /*@Query(value = "select new clamos.io.dashboard.dto.SchoolDTO (m.survey_base_date" +
            ", coalesce(sum(case when m.schl_type = '유치원' then m.schl_count else 0 end), 0)  " +
            ", coalesce(sum(case when m.schl_type = '초등학교' then m.schl_count else 0 end), 0)   " +
            ", coalesce(sum(case when m.schl_type = '중학교' then m.schl_count else 0 end), 0)  " +
            ", coalesce(sum(case when m.schl_type = '고등학교' then m.schl_count else 0 end), 0))  " +
            "from SchoolEntity m " +
            "where m.schl_exist_status <> '폐(원)교' " +
            "and m.main_or_branch_school <> '분교장' " +
            "and m.admdst= '파주시' " +
            "group by m.survey_base_date")
    List<SchoolDTO> Chart_1();
*/

    /*@Query(value =
            "select new clamos.io.dashboard.dto.SchoolDTO (m.survey_base_date, " +
            "coalesce(sum(case when m.schl_type = '유치원' then m.schl_count else 0 end), 0), " +
            "coalesce(sum(case when m.schl_type = '초등학교' then m.schl_count else 0 end), 0), " +
            "coalesce(sum(case when m.schl_type = '중학교' then m.schl_count else 0 end), 0), " +
            "coalesce(sum(case when m.schl_type = '고등학교' then m.schl_count else 0 end), 0))  " +
            "from SchoolEntity m " +
            "where m.schl_exist_status <> '폐(원)교' " +
            "and m.main_or_branch_school <> '분교장' " +
            "and m.admdst= '파주시' " +
            "group by m.survey_base_date")
    List<SchoolDTO> Chart_1();*/

     @Query(value = "select survey_base_date as survey_base_date" +
            ", coalesce(sum(case when schl_type = '유치원' then schl_count else 0 end), 0) as kinder_cnt " +
            ", coalesce(sum(case when schl_type = '초등학교' then schl_count else 0 end), 0) as ele_cnt " +
            ", coalesce(sum(case when schl_type = '중학교' then schl_count else 0 end), 0) as mid_cnt " +
            ", coalesce(sum(case when schl_type = '고등학교' then schl_count else 0 end), 0) as high_cnt " +
            "from tb_schl_stat_kms " +
            "where schl_exist_status <> '폐(원)교' " +
            "and main_or_branch_school <> '분교장' " +
            "and admdst= '파주시' " +
            "group by survey_base_date" +
            "", nativeQuery = true)
    List<Integer[]> Chart_1();

}
