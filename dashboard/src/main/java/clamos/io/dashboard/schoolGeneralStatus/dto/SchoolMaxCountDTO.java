package clamos.io.dashboard.schoolGeneralStatus.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SchoolMaxCountDTO {

    private String name;
    private Long total_cnt;
//    private String schoolType;

}
