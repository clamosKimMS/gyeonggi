package clamos.io.dashboard.multiDropoutStatus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class High_Id implements Serializable {

    @Column(name = "shcl_nm")
    private String shcl_nm;

    @Column(name = "schl_type")
    private String schl_type;

    @Column(name = "grade")
    private String grade;

}
