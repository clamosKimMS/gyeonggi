package clamos.io.dashboard.teacherGeneralStatus.controller;

import clamos.io.dashboard.teacherGeneralStatus.dto.TeacherGeneralStatusDTO;
import clamos.io.dashboard.teacherGeneralStatus.service.TeacherGeneralStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/gyeonggi")
public class TeacherGeneralStatusController {

    @Autowired
    private final TeacherGeneralStatusService service;

    // 행정구역별
    @GetMapping("/getLocalTeacherMaxTotal/{type}")
    public Integer controller_getLocalTeacherMaxTotal(@PathVariable String type) {
        System.out.println("학교타입 : " + type);
        return service.getMaxTeacherArea(type);
    }
    @GetMapping("/getLocalTeacherMaxList/{type}")
    public List<TeacherGeneralStatusDTO> controller_getLocalTeacherMaxList(@PathVariable String type) {
        return service.getListTeacherArea(type);
    }

    // 지역청별
    @GetMapping("/getEduTeacherMaxTotal/{type}")
    public Integer controller_getEduTeacherMaxTotal(@PathVariable String type) {
        System.out.println("학교타입 : " + type);
        return service.getMaxTeacherEdu(type);
    }
    @GetMapping("/getEduTeacherMaxList/{type}")
    public List<TeacherGeneralStatusDTO> controller_getEduTeacherMaxList(@PathVariable String type) {
        return service.getListTeacherEdu(type);
    }

}
