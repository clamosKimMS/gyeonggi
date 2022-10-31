package clamos.io.dashboard.multiculturalFamilyStudent.controller;

import clamos.io.dashboard.multiculturalFamilyStudent.dto.MulticulturalFamilyDTO;
import clamos.io.dashboard.multiculturalFamilyStudent.service.MulticulturalFamilyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/gyeonggi")
public class MulticulturalFamilyController {

    private final MulticulturalFamilyService service;

    // 행정구역
    @GetMapping("/getLocalMultiFmMaxTotal/{year}")
    public Integer controller_getLocalMultiFmMaxTotal(@PathVariable String year) {
        System.out.println("연도 : " + year);
        return service.getMaxMultiFmArea(year);
    }

    @GetMapping("/getLocalMultiFmMaxList/{year}")
    public List<MulticulturalFamilyDTO> controller_getLocalMultiFmMaxList(@PathVariable String year) {
        return service.getListMultiFmArea(year);
    }

    // 지역청별
    @GetMapping("/getEduMultiFmMaxTotal/{year}")
    public Integer controller_getEduMultiFmMaxTotal(@PathVariable String year) {
        System.out.println("연도 : " + year);
        return service.getMaxMultiFmEdu(year);
    }
    @GetMapping("/getEduMultiFmMaxList/{year}")
    public List<MulticulturalFamilyDTO> controller_getEduMultiFmMaxList(@PathVariable String year) {
        return service.getListMultiFmEdu(year);
    }
}
