package clamos.io.dashboard.teacherGeneralStatus.dto;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TeacherGeneralStatusDTO {

    private String admdst;
    private Integer tchr_tot_sum;

}
