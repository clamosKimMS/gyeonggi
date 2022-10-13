package clamos.io.dashboard.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
@Table(name = "tb_schl_stat_kms")
public class SchoolEntity {

    @Id
    @Column(name = "idx")
    private Integer idx;

    @Column(name = "ctpv")
    private String ctpv;

    @Column(name = "admdst")
    private String admdst;

    @Column(name = "schl_type")
    private String schl_type;

    @Column(name = "survey_base_date")
    private Integer survey_base_date;

    @Column(name = "schl_count")
    private Integer schl_count;

    @Column(name = "schl_exist_status")
    private String schl_exist_status;

    @Column(name = "main_or_branch_school")
    private String main_or_branch_school;

}
