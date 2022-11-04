package clamos.io.dashboard.schoolAfterCare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SchlAfterCareDTO {

    private String schl_stat_regional_area;
    private Long aftr_schl_care_oprtn_yn_count;

}
