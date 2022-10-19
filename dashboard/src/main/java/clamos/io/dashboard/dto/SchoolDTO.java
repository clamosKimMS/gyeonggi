package clamos.io.dashboard.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SchoolDTO {

    private Integer survey_base_date;
    private Integer kinder_cnt;
    private Integer ele_cnt;
    private Integer mid_cnt;
    private Integer high_cnt;

}