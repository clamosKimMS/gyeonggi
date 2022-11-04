package clamos.io.dashboard.schoolAfterCare.service;

import static org.junit.jupiter.api.Assertions.*;

import clamos.io.dashboard.schoolAfterCare.dto.SchlAfterCareDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchlAfterCareServiceImplTest {

    @Autowired
    SchlAfterCareService service;

    @Test
    void getLocalSchlAfterCareList() {
        String type = "emh";
        List<SchlAfterCareDTO> dtoList = service.getLocalSchlAfterCareList(type);

        for (SchlAfterCareDTO dto : dtoList) {
            System.out.println(dto);
        }
    }

    @Test
    @DisplayName("maxTest")
    void getLocalSchlAfterCareMax() {
        String type = "emh";
        Long max = service.getLocalSchlAfterCareMax(type);
        System.out.println(max);
    }

    @Test
    void getEduSchlAfterCareList() {

        String type = "emh";
        List<SchlAfterCareDTO> dtoList = service.getEduSchlAfterCareList(type);

        for (SchlAfterCareDTO dto : dtoList) {
            System.out.println(dto);
        }
    }

    @Test
    void getEduSchlAfterCareMax() {

        String type = "emh";
        Long max = service.getEduSchlAfterCareMax(type);
        System.out.println(max);

    }
}