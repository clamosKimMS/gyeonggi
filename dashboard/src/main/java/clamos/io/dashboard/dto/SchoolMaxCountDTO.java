package clamos.io.dashboard.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SchoolMaxCountDTO {

    private String name;
    private Integer total_cnt;

}
