package clamos.io.dashboard.schoolAfterCare.controller;

import clamos.io.dashboard.schoolAfterCare.dto.SchlAfterCareDTO;
import clamos.io.dashboard.schoolAfterCare.service.SchlAfterCareService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gyeonggi")
@Log4j2
@CrossOrigin("*")
public class ScholAfterCareController {

    private final SchlAfterCareService service;

    // 행정구역
    @GetMapping("/getLocalSchlAfterCareMax/{type}")
    public Long controller_getLocalSchlAfterCareMax(@PathVariable String type) {
        System.out.println("학교타입 : " + type);
        return service.getLocalSchlAfterCareMax(type);
    }
    @GetMapping("/getLocalSchlAfterCareList/{type}")
    public List<SchlAfterCareDTO> controller_getLocalSchlAfterCareList(@PathVariable String type) {
        return service.getLocalSchlAfterCareList(type);
    }

    // 지역청
    @GetMapping("/getEduSchlAfterCareMax/{type}")
    public Long controller_getEduSchlAfterCareMax(@PathVariable String type) {
        System.out.println("학교타입 : " + type);
        return service.getEduSchlAfterCareMax(type);
    }
    @GetMapping("/getEduSchlAfterCareList/{type}")
    public List<SchlAfterCareDTO> controller_getEduSchlAfterCareList(@PathVariable String type) {
        return service.getEduSchlAfterCareList(type);
    }

}
