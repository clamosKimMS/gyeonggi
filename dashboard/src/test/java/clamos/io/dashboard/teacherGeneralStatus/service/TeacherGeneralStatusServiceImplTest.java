package clamos.io.dashboard.teacherGeneralStatus.service;

import clamos.io.dashboard.teacherGeneralStatus.dto.TeacherGeneralStatusDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TeacherGeneralStatusServiceImplTest {

    @Autowired
    private TeacherGeneralStatusService service;

    @Test
    void getMaxTeacherMaxArea() {

        Integer max = service.getMaxTeacherMaxArea("ke");
        Assertions.assertEquals(3126, max);
    }

    @Test
    void getListTeacherArea() {
        List<TeacherGeneralStatusDTO> dtoList = service.getListTeacherArea("ke");

        for (TeacherGeneralStatusDTO dto : dtoList) {
            System.out.println(dto);
        }
    }

    @Test
    void getMaxTeacherMaxEdu() {

        Integer max = service.getMaxTeacherMaxEdu("ke");
        Assertions.assertEquals(3773, max);

    }

    @Test
    void getListTeacherEdu() {
        List<TeacherGeneralStatusDTO> dtoList = service.getListTeacherEdu("ke");

        for (TeacherGeneralStatusDTO dto : dtoList) {
            System.out.println(dto);
        }

    }
}