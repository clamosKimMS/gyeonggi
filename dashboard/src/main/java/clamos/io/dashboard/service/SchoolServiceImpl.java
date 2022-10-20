package clamos.io.dashboard.service;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolInterfaceDTO;
import clamos.io.dashboard.dto.SchoolMaxCountDTO;
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
    public List<SchoolDTO> getSchoolCount(String Area) {

        List<Integer[]> result_tmp = repository.Chart_1(Area);

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

    @Override
    public Integer getMaxSchoolTotal() {

        return repository.maxTotal();

    }

    @Override
    public Integer getAreaSearchCount(String Area) {

        return repository.areaSearchCount(Area);

    }

    @Override
    public List<SchoolMaxCountDTO> getSchoolCountList() {

        List<String[]> result_tmp = repository.areaSearchCountList();
        List<SchoolMaxCountDTO> result = new ArrayList<>();

        for (String[] dto : result_tmp) {
            SchoolMaxCountDTO school = SchoolMaxCountDTO.builder()
                    .name(dto[0])
                    .total_cnt(Integer.parseInt(dto[1]))
                    .build();

            result.add(school);
        }

        return result;

    }

}
