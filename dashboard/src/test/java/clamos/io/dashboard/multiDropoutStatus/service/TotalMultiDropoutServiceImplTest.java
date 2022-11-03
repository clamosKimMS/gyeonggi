package clamos.io.dashboard.multiDropoutStatus.service;

import clamos.io.dashboard.multiDropoutStatus.dto.TotalMultiDropoutDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TotalMultiDropoutServiceImplTest {

    @Autowired
    private TotalMultiDropoutService service;

    @Test
    @DisplayName("행정구역 List")
    void getLocalTotalDropoutList() {
        List<TotalMultiDropoutDTO> dtoList = service.getLocalTotalDropoutList("2020");

        for (TotalMultiDropoutDTO dto : dtoList) {
            System.out.println(dto);
        }
    }

    @Test
    void getLocalTotalDropoutMax() {

        Integer max = service.getLocalTotalDropoutMax("2020");
        System.out.println(max);
        Assertions.assertEquals(9964, max);

    }

    @Test
    void getEduTotalDropoutList() {

        List<TotalMultiDropoutDTO> dtoList = service.getEduTotalDropoutList("2020");
        for (TotalMultiDropoutDTO dto : dtoList) {
            System.out.println(dto);
        }

    }

    @Test
    void getEduTotalDropoutMax() {

        Integer max = service.getEduTotalDropoutMax("2020");
        System.out.println(max);
        Assertions.assertEquals(9964, max);
    }

}