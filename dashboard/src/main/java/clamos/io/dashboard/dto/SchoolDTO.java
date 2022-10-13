package clamos.io.dashboard.dto;

import clamos.io.dashboard.entity.SchoolEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolDTO {

    private Integer idx;
    private String ctpv;
    private String admdst;
    private String schl_type;
    private Integer survey_base_date;
    private Integer schl_count;
    private String schl_exist_status;
    private String main_or_branch_school;


    public static SchoolDTO fromEntity(SchoolEntity entity) {

        return SchoolDTO.builder()
                .idx(entity.getIdx())
                .ctpv(entity.getCtpv())
                .admdst(entity.getAdmdst())
                .schl_type(entity.getSchl_type())
                .survey_base_date(entity.getSurvey_base_date())
                .schl_count(entity.getSchl_count())
                .schl_exist_status(entity.getSchl_exist_status())
                .main_or_branch_school(entity.getMain_or_branch_school())
                .build();

    }

}
