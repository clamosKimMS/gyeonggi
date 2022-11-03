package clamos.io.dashboard.multiDropoutStatus.service;

import clamos.io.dashboard.multiDropoutStatus.dto.MidMultiDropoutDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MidMultiDropoutServiceImplTest {

    @Autowired
    private MidMultiDropoutService service;

    @Test
    @DisplayName("중학교 행중구역 List")
    void getLocalMidDropoutList() {

        List<MidMultiDropoutDTO> dtoList = service.getLocalMidDropoutList("2020");
        for (MidMultiDropoutDTO dto : dtoList) {
            System.out.println(dto);
        }

    }

    @Test
    @DisplayName("중학교 지역청 List")
    void getEduMidDropoutList() {

        List<MidMultiDropoutDTO> dtoList = service.getEduMidDropoutList("2020");
        for (MidMultiDropoutDTO dto : dtoList) {
            System.out.println(dto);
        }

    }
}