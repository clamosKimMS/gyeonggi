package clamos.io.dashboard.multiCultureTime.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
@Table(name = "tb_stu_multi_culture")
public class MultiCultureTimeEntity {

    @EmbeddedId
    private MultiCultureTimeId id;

    @Column(name = "sigun")
    private String sigun;

    @Column(name = "multi_culture_stu_cnt")
    private Integer multi_culture_stu_cnt;

    @Column(name = "ctpv")
    private String ctpv;

    @Column(name = "yr")
    private String yr;
}
