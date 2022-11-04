package clamos.io.dashboard.multiCultureTime.service;

import clamos.io.dashboard.multiCultureTime.dto.MultiCultureTimeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MultiCultureTimeServiceImplTest {

    @Autowired
    private MultiCultureTimeService service;

    @Test
    @DisplayName("다문화가정 시계열 행정구역별 MaxList")
    void getLocalMultiCultureTimeMax() {

        Integer max = service.getLocalMultiCultureTimeMax();
        System.out.println(max);
        Assertions.assertEquals(6240, max);
    }

    @Test
    void getLocalMultiCultureTimeList() {

        List<MultiCultureTimeDTO> dtoList = service.getLocalMultiCultureTimeList();

        for (MultiCultureTimeDTO dto : dtoList) {
            System.out.println(dto);
        }

    }

    @Test
    void getEduMultiCultureTimeMax() {

        Integer max = service.getEduMultiCultureTimeMax();
        System.out.println(max);
        Assertions.assertEquals(6240, max);

    }

    @Test
    void getEduMultiCultureTimeList() {

        List<MultiCultureTimeDTO> dtoList = service.getEduMultiCultureTimeList();

        for (MultiCultureTimeDTO dto : dtoList) {
            System.out.println(dto);
        }

    }



    /*@Test
    @DisplayName("한반도 MAX")
    public void Test() {

        Integer max = service.getKoreaMultiCultureTimeMax();
        Assertions.assertEquals(6240, max);

    }*/

}