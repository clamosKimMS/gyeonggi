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
public class YoungFareDto {

     private String faclt_nm;

     private String refine_roadnm_addr;

     private String refine_lotno_addr;

     private String refine_wgs84_lat;

     private String refine_wgs84_logt;

     private String contct_no;

     private Integer aceptnc_psn_capa;


     // 리스트 DTO
     public YoungFareDto(String faclt_nm, String refine_roadnm_addr, String refine_wgs84_lat,
         String refine_wgs84_logt) {
          this.faclt_nm = faclt_nm;
          this.refine_roadnm_addr = refine_roadnm_addr;
          this.refine_wgs84_lat = refine_wgs84_lat;
          this.refine_wgs84_logt = refine_wgs84_logt;
     }

     // 상세내용 DTO
     public YoungFareDto(String contct_no, Integer aceptnc_psn_capa, String refine_roadnm_addr,
         String refine_lotno_addr) {
          this.contct_no = contct_no;
          this.aceptnc_psn_capa = aceptnc_psn_capa;
          this.refine_roadnm_addr = refine_roadnm_addr;
          this.refine_lotno_addr = refine_lotno_addr;
     }

}
