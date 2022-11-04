package clamos.io.dashboard.multiCultureTime.controller;

import clamos.io.dashboard.multiCultureTime.dto.MultiCultureTimeDTO;
import clamos.io.dashboard.multiCultureTime.service.MultiCultureTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gyeonggi")
@CrossOrigin("*")
@Log4j2
public class MultiCultureTimeController {

    private final MultiCultureTimeService service;

    // 행정구역
    @GetMapping("/getLocalMultiTimeMax")
    public Integer controller_getLocalMultiTimeMax() {
        return service.getLocalMultiCultureTimeMax();
    }
    @GetMapping("/getLocalMultiTimeList")
    public List<MultiCultureTimeDTO> controller_getLocalMultiTimeList() {
        return service.getLocalMultiCultureTimeList();
    }

    // 지역청별
    @GetMapping("/getEduMultiTimeMax")
    public Integer controller_getEduMultiTimeMax() {
        return service.getEduMultiCultureTimeMax();
    }
    @GetMapping("/getEduMultiTimeList")
    public List<MultiCultureTimeDTO> controller_getEduMultiTimeList() {
        return service.getEduMultiCultureTimeList();
    }
}
