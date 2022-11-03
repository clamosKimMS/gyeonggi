package clamos.io.dashboard.multiDropoutStatus.service;

import clamos.io.dashboard.multiDropoutStatus.dto.EleMultiDropoutDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EleMultiDropoutServiceImplTest {

    @Autowired
    private EleMultiDropoutService service;

    @Test
    @DisplayName("초등학교 행정구역 List Test")
    public void getLocalElementDropoutList() {
        List<EleMultiDropoutDTO> dtoList = service.getLocalElementDropoutList("2020");

        for (EleMultiDropoutDTO dto : dtoList) {
            System.out.println(dto);
        }
    }

    @Test
    @DisplayName("초등학교 지역별 List Test")
    public void getEduElementDropoutList() {

        List<EleMultiDropoutDTO> dtoList = service.getEduElementDropoutList("2020");

        for (EleMultiDropoutDTO dto : dtoList) {
            System.out.println(dto);
        }

    }

}