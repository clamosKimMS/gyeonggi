package clamos.io.dashboard.multiculturalFamilyStudent.controller;

import clamos.io.dashboard.multiculturalFamilyStudent.dto.MulticulturalFmDTO;
import clamos.io.dashboard.multiculturalFamilyStudent.service.MulticulturalFmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/gyeonggi")
public class MulticulturalFmController {

    private final MulticulturalFmService service;

    // 행정구역
    @GetMapping("/getLocalMultiFmMaxTotal/{year}")
    public Integer controller_getLocalMultiFmMaxTotal(@PathVariable String year) {
        System.out.println("연도 : " + year);
        return service.getMaxMultiFmArea(year);
    }
    @GetMapping("/getLocalMultiFmMaxList/{year}")
    public List<MulticulturalFmDTO> controller_getLocalMultiFmMaxList(@PathVariable String year) {
        return service.getListMultiFmArea(year);
    }

    // 지역청별
    @GetMapping("/getEduMultiFmMaxTotal/{year}")
    public Integer controller_getEduMultiFmMaxTotal(@PathVariable String year) {
        System.out.println("연도 : " + year);
        return service.getMaxMultiFmEdu(year);
    }
    @GetMapping("/getEduMultiFmMaxList/{year}")
    public List<MulticulturalFmDTO> controller_getEduMultiFmMaxList(@PathVariable String year) {
        return service.getListMultiFmEdu(year);
    }

}
