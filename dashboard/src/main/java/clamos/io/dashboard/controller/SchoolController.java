package clamos.io.dashboard.controller;

import clamos.io.dashboard.dto.SchoolDTO;
import clamos.io.dashboard.dto.SchoolInterfaceDTO;
import clamos.io.dashboard.dto.SchoolMaxCountDTO;
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

    @GetMapping("/getMaxTotal")
    public Integer controller_getMaxSchoolTotal() {

        return service.getMaxSchoolTotal();


    }

    @GetMapping("/searchPlaceCount/{Area}")
    public Integer controller_searchPlaceCount(@PathVariable String Area ) {

        System.out.println("선택된 도시 : " + Area);
        System.out.println("도시의 합   : " + service.getAreaSearchCount(Area));
        return service.getAreaSearchCount(Area);
    }

    @GetMapping("/getSchoolTotalCountList")
    public List<SchoolMaxCountDTO> controller_getSchoolCount() {

        return service.getSchoolCountList();

    }

}
