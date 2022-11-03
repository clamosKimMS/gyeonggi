package clamos.io.dashboard.multiDropoutStatus.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class EleMultiDropoutDTO {

    private String site_nm;
    private Integer ppltn_sum_total;

}
