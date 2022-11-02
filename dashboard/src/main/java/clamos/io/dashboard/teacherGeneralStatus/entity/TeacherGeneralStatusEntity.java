package clamos.io.dashboard.teacherGeneralStatus.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
@Table(name = "tb_schl_stat_kms")
public class TeacherGeneralStatusEntity {

    @Id
    @Column(name = "idx")
    private Integer idx;

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx_kms;*/

    @Column(name = "admdst")
    private String admdst;

    @Column(name = "tchr_tot")
    private Integer tchr_tot;

    @Column(name = "schl_type")
    private String schl_type;

    @Column(name = "survey_base_date")
    private String survey_base_date;

}
