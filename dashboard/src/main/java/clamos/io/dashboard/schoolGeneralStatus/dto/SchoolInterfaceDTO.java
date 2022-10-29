package clamos.io.dashboard.schoolGeneralStatus.dto;

import lombok.Setter;

import java.util.List;


public interface SchoolInterfaceDTO {

    Integer survey_base_date();
    Integer kinder_cnt();
    Integer ele_cnt();
    Integer mid_cnt();
    Integer high_cnt();

}
