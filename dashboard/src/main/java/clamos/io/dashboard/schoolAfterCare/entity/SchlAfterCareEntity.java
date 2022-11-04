package clamos.io.dashboard.schoolAfterCare.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "tb_schl_aftercare")
public class SchlAfterCareEntity {

    @EmbeddedId
    private SchlAfterCareId id;

    @Column(name = "schl_stat_regional_area")
    private String schl_stat_regional_area;

    @Column(name = "aftr_schl_care_oprtn_yn")
    private String aftr_schl_care_oprtn_yn;

    @Column(name = "schl_stat_schl_type")
    private String schl_stat_schl_type;

    @Column(name = "yr")
    private String yr;

}
