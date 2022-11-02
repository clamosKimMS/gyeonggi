package clamos.io.dashboard.studentStatus.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
@Table(name = "tb_schl_stat_kms")
public class StudentStatusEntity {

    @Id
    @Column(name = "idx")
    private Integer idx;

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx_kms;*/

    @Column(name = "admdst")
    private String admdst;

    @Column(name = "stdnt_tot_by_grade")
    private Integer stdnt_tot_by_grade;

    @Column(name = "survey_base_date")
    private String survey_base_date;

    @Column(name = "schl_type")
    private String schl_type;

}