package clamos.io.dashboard.multiDropoutStatus.service;

import clamos.io.dashboard.multiDropoutStatus.dto.HighMultiDropoutDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HighMultiDropoutServiceImplTest {

    @Autowired
    private HighMultiDropoutService service;

    @Test
    @DisplayName("고등학교 행정구역 테스트")
    void getLocalHighDropoutList() {

        List<HighMultiDropoutDTO> dtoList = service.getLocalHighDropoutList("2020");

        for (HighMultiDropoutDTO dto : dtoList) {
            System.out.println(dto);
        }

    }

    @Test
    @DisplayName("고등학교 지역별 테스트")
    void getEduHighDropoutList() {

        List<HighMultiDropoutDTO> dtoList = service.getEduHighDropoutList("2020");

        for (HighMultiDropoutDTO dto : dtoList) {
            System.out.println(dto);
        }

    }
}