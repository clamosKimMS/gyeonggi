package clamos.io.dashboard.repository;


import clamos.io.dashboard.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Integer> {

    @Query("select m.survey_base_date" +
            ", coalesce(sum(case when m.schl_type = '유치원' then m.schl_count else 0 end), 0) " +
            ", coalesce(sum(case when m.schl_type = '초등학교' then m.schl_count else 0 end), 0) " +
            ", coalesce(sum(case when m.schl_type = '중학교' then m.schl_count else 0 end), 0)  " +
            ", coalesce(sum(case when m.schl_type = '고등학교' then m.schl_count else 0 end), 0)  " +
            "from SchoolEntity m " +
            "where m.schl_exist_status <> '폐(원)교' " +
            "and m.main_or_branch_school <> '분교장' " +
            "and m.admdst= '파주시' " +
            "group by m.survey_base_date ")
    List<Integer[]> Chart_1();

//    List<Object[]> Chart_1(@Param("admdst") String admdst);

}
