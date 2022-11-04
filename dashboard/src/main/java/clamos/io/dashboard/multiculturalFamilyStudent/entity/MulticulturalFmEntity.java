package clamos.io.dashboard.multiculturalFamilyStudent.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
@Table(name = "tb_edu_stat_kms")
public class MulticulturalFmEntity {

    @Id
    @Column(name = "idx")
    private Integer idx;

    @Column(name = "yr")
    private String yr;

    @Column(name = "admdst")
    private String admdst;

    @Column(name = "mc_stdnt_tot")
    private Integer mc_stdnt_tot;

}
