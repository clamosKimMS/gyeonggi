package clamos.io.dashboard.dto;

import clamos.io.dashboard.entity.SchoolEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class SchoolDTO {

    private Integer survey_base_date;
    private Integer kinder_cnt;
    private Integer ele_cnt;
    private Integer mid_cnt;
    private Integer high_cnt;

    public SchoolDTO(Integer survey_base_date, Integer kinder_cnt, Integer ele_cnt, Integer mid_cnt, Integer high_cnt) {
        this.survey_base_date = survey_base_date;
        this.kinder_cnt = kinder_cnt;
        this.ele_cnt = ele_cnt;
        this.mid_cnt = mid_cnt;
        this.high_cnt = high_cnt;
    }

}
