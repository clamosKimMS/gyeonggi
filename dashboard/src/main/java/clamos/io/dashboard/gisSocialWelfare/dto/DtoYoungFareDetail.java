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
public class DtoYoungFareDetail {

     private String contct_no;
     private Integer aceptnc_psn_capa;
     private String refine_roadnm_addr;
     private String refine_lotno_addr;

}
