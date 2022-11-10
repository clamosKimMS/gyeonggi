package clamos.io.dashboard.gisSocialWelfare.dto;

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
public class DtoMindHealDetail {

     private String reprsnt_tel_no;
     private String inst_nm;
     private String faclt_div_nm;
     private String refine_roadnm_addr;

}
