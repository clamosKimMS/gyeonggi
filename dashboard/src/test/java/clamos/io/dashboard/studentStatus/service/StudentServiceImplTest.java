package clamos.io.dashboard.studentStatus.service;

import clamos.io.dashboard.studentStatus.dto.StudentStatusDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    private StudentService service;

    // 행정구역 Test
    @Test
    @DisplayName("행정구역 MAX")
    void getMaxStudentArea() {
        String type = "kemh";
        Integer result = service.getMaxStudentArea(type);
        Assertions.assertEquals(70507, result);
    }
    @Test
    @DisplayName("행정구역별 Max List")
    void getListStudentArea() {
        String type = "kemh";
        List<StudentStatusDTO> dtoList = service.getListStudentArea(type);
        for (StudentStatusDTO dto : dtoList) {
            System.out.println(dto);
        }
    }

    // 지역청 Test
    @Test
    @DisplayName("지역청 MAX")
    void getMaxStudentEdu() {
        String type = "kemh";
        Integer result = service.getMaxStudentEdu(type);
        Assertions.assertEquals(84576, result);
    }
    @Test
    @DisplayName("지역청별 Max List")
    void getListStudentEdu() {
        String type = "kemh";
        List<StudentStatusDTO> dtoList = service.getListStudentEdu(type);

        for (StudentStatusDTO dto : dtoList) {
            System.out.println(dto);
        }

    }
}