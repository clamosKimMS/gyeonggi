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
public class DtoYoungFareList {

     private String faclt_nm;
     private String refine_roadnm_addr;
     private String refine_wgs84_lat;
     private String refine_wgs84_logt;

}
