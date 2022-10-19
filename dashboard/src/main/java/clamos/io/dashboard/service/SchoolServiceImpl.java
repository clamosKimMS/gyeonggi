package clamos.io.dashboard.service;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolInterfaceDTO;
import clamos.io.dashboard.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService{

    private final SchoolRepository repository;

    @Override
    public List<SchoolDTO> getSchoolCount() {

        List<Integer[]> result_tmp = repository.Chart_1();

        List<SchoolDTO> dto = new ArrayList<>();

        for (Integer[] list : result_tmp) {

            SchoolDTO dto_tmp = SchoolDTO.builder()
                    .survey_base_date(list[0])
                    .kinder_cnt(list[1])
                    .ele_cnt(list[2])
                    .mid_cnt(list[3])
                    .high_cnt(list[4])
                    .build();

            dto.add(dto_tmp);
        }

        return dto;

    }

}
