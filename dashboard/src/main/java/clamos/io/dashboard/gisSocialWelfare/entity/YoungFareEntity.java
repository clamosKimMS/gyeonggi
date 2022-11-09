package clamos.io.dashboard.gisSocialWelfare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_etl_youngboyandgirswelfare")
@ToString
public class YoungFareEntity {

     // 전화번호
     @Id
     @Column(name = "contct_no")
     private String contct_no;

     // 시설명
     @Column(name = "faclt_nm")
     private String faclt_nm;

     // 도로명 주소
     @Column(name = "refine_roadnm_addr")
     private String refine_roadnm_addr;

     // 지번 주소
     @Column(name = "refine_lotno_addr")
     private String refine_lotno_addr;

     // 위도
     @Column(name = "refine_wgs84_lat")
     private String refine_wgs84_lat;

     // 경도
     @Column(name = "refine_wgs84_logt")
     private String refine_wgs84_logt;

     // 시군명
     @Column(name = "sigun_nm")
     private String sigun_nm;

     // 수용인원
     @Column(name = "aceptnc_psn_capa")
     private Integer aceptnc_psn_capa;

}
