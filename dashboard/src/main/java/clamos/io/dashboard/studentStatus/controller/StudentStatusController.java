package clamos.io.dashboard.studentStatus.controller;

import clamos.io.dashboard.studentStatus.dto.StudentStatusDTO;
import clamos.io.dashboard.studentStatus.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/gyeonggi")
public class StudentStatusController {

    private final StudentService service;

    // 행정구역
    @GetMapping("/getLocalStudentMaxTotal/{type}")
    public Integer controller_getLocalStudentMaxTotal(@PathVariable String type) {
        System.out.println("학교타입 : " + type);
        return service.getMaxStudentArea(type);
    }

    @GetMapping("/getLocalStudentMaxList/{type}")
    public List<StudentStatusDTO> controller_getLocalStudentMaxList(@PathVariable String type) {
        return service.getListStudentArea(type);
    }

    // 지역청별
    @GetMapping("/getEduStudentMaxTotal/{type}")
    public Integer controller_getEduStudentMaxTotal(@PathVariable String type) {
        System.out.println("학교타입 : " + type);
        return service.getMaxStudentEdu(type);
    }

    @GetMapping("/getEduStudentMaxList/{type}")
    public List<StudentStatusDTO> controller_getEduStudentMaxList(@PathVariable String type) {
        return service.getListStudentEdu(type);
    }


}
