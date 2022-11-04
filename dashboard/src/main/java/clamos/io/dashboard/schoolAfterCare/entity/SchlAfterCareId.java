package clamos.io.dashboard.schoolAfterCare.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class SchlAfterCareId implements Serializable {

    @Column(name = "schl_stat_schl_nm")
    private String schl_stat_schl_nm;

    @Column(name = "schl_stat_regional_size")
    private String schl_stat_regional_size;

}
