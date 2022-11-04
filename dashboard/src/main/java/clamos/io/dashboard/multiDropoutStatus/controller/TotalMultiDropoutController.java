package clamos.io.dashboard.multiDropoutStatus.controller;

import clamos.io.dashboard.multiDropoutStatus.dto.TotalMultiDropoutDTO;
import clamos.io.dashboard.multiDropoutStatus.service.TotalMultiDropoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/gyeonggi")
public class TotalMultiDropoutController {

    private final TotalMultiDropoutService service;

    // 행정구역
    @GetMapping("/getLocalMultiDropoutTotal/{year}")
    public Integer controller_getLocalMultiDropoutTotal(@PathVariable String year) {
        System.out.println("연도 : " + year);
        return service.getLocalTotalDropoutMax(year);
    }
    @GetMapping("/getLocalMultiDropoutList/{year}")
    public List<TotalMultiDropoutDTO> controller_getLocalMultiDropoutList(@PathVariable String year) {
        return service.getLocalTotalDropoutList(year);
    }

    // 지역청별
    @GetMapping("/getEduMultiDropoutTotal/{year}")
    public Integer controller_getEduMultiDropoutTotal(@PathVariable String year) {
        System.out.println("연도 : " + year);
        return service.getEduTotalDropoutMax(year);
    }
    @GetMapping("/getEduMultiDropoutList/{year}")
    public List<TotalMultiDropoutDTO> controller_getEduMultiDropoutList(@PathVariable String year) {
        return service.getEduTotalDropoutList(year);
    }

}
