package clamos.io.dashboard.multiCultureTime.entity;

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
public class MultiCultureTimeId implements Serializable {

    @Column(name = "kedi_code")
    private String kedi_code;

    @Column(name = "grade")
    private String grade;

}
