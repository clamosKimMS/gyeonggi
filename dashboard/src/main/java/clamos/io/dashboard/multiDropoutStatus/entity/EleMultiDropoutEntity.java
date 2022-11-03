package clamos.io.dashboard.multiDropoutStatus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_multi_cul_schl_dropout_stat_ele")
public class EleMultiDropoutEntity {

    @EmbeddedId
    private Element_Id id;

    @Column(name = "site_nm")
    private String site_nm;

    @Column(name = "ppltn_sum")
    private Integer ppltn_sum;

    @Column(name = "yr")
    private String yr;

}
