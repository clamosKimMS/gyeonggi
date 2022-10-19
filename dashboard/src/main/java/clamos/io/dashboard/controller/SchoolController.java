package clamos.io.dashboard.controller;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolInterfaceDTO;
import clamos.io.dashboard.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/gyeonggi")
public class SchoolController {

    private final SchoolService service;

    @GetMapping("/schoolTypeCount/{Area}")
    public List<SchoolDTO> controller_getSumSchool(@PathVariable String Area) {

        System.out.println("클릭한 도시 : " + Area);

        return service.getSchoolCount(Area);

    }
}
