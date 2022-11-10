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
@ToString
@Table(name = "tb_etl_mindhealthinstitution")
public class MindHealEntity {

     // 전화번호
     @Id
     @Column(name = "reprsnt_telno")
     private String reprsnt_telno;

     // 시설명
     @Column(name = "inst_nm")
     private String inst_nm;

     // 도로명 주소
     @Column(name = "refine_roadnm_addr")
     private String refine_roadnm_addr;

     // 위도
     @Column(name = "refine_wgs84_lat")
     private String refine_wgs84_lat;

     // 경도
     @Column(name = "refine_wgs84_logt")
     private String refine_wgs84_logt;

     // 시군명
     @Column(name = "sigun_nm")
     private String sigun_nm;

     // 시설구분명
     @Column(name = "faclt_div_nm")
     private String faclt_div_nm;

}
