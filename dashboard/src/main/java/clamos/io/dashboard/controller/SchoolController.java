package clamos.io.dashboard.controller;

import clamos.io.dashboard.dto.SchoolDTO;
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

    /* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ ImageMap에 필요한 Controller ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */

    // 행정구역별
    @GetMapping("/getLocalMaxTotal/{type}")
    public Long controller_getMaxSchoolTotal(@PathVariable String type) {
        System.out.println("Max 학교유형 : " + type);
        return service.getAreaMaxSchoolTotal(type);
    }
    @GetMapping("/getLocalSchoolTotalCountList/{type}")
    public List<SchoolMaxCountDTO> controller_getSchoolCount(@PathVariable String type) {
        System.out.println("List 학교유형 :" + type);
        return service.getAreaSchoolCountList(type);
    }


    // 지역청별
    @GetMapping("/getEduMaxTotal/{type}")
    public Long controller_getEduMaxSchoolTotal(@PathVariable String type) {
        return service.getEduMaxSchoolTotal(type);
    }
    @GetMapping("/getEduSchoolTotalCountList/{type}")
    public List<SchoolMaxCountDTO> controller_getEduSchoolCount(@PathVariable String type) {
        return service.getEduSchoolCountList(type);
    }



    /* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Chart 그리는데에 사용할 Controller ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
    @GetMapping("/schoolTypeCount/{Area}")
    public List<SchoolDTO> controller_getSumSchool(@PathVariable String Area) {
        System.out.println("클릭한 도시 : " + Area);
        return service.getSchoolCount(Area);
    }
    @GetMapping("/searchPlaceCount/{Area}")
    public Long controller_searchPlaceCount(@PathVariable String Area ) {
        System.out.println("선택된 도시 : " + Area);
        System.out.println("도시의 합   : " + service.getAreaSearchCount(Area));
        return service.getAreaSearchCount(Area);
    }


}
