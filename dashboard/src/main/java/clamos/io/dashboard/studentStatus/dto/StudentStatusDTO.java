package clamos.io.dashboard.studentStatus.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StudentStatusDTO {

    private String admdst;
    private Integer stdnt_tot_by_grade_sum;

}