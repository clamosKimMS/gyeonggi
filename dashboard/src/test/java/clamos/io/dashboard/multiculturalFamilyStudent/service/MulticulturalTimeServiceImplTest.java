package clamos.io.dashboard.multiculturalFamilyStudent.service;

import clamos.io.dashboard.multiculturalFamilyStudent.dto.MulticulturalFmDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.List;

@Log4j2
@SpringBootTest
class MulticulturalTimeServiceImplTest {

    @Autowired
    private MulticulturalFmService service;

    @Autowired
    EntityManager em;

    /* 행정구역 */
    @Test
    @DisplayName("다문화 행정구역 MAX ")
    public void getMaxMultiFmArea() {

        String yr = "2022";

        Integer result = service.getMaxMultiFmArea(yr);

        Assertions.assertEquals(12990, result);

    }

    @Test
    @DisplayName("다문화 행정구역별 Max List")
    public void getListMultiFmArea() {

        String yr = "2022";

        List<MulticulturalFmDTO> dtoList = service.getListMultiFmArea(yr);

        for (MulticulturalFmDTO dto : dtoList) {
            System.out.println(dto);
        }

    }


    /* 지역청 */
    @Test
    @DisplayName("다문화 지역청 MAX")
    public void getMaxMultiFmEdu() {

        String yr = "2022";

        Integer result = service.getMaxMultiFmEdu(yr);
        log.info(result);

        Assertions.assertEquals(12990, result);


    }

    @Test
    @DisplayName("다문화 지역청별 Max List")
    public void getListMultiFmEdu() {

        String yr = "2022";

        List<MulticulturalFmDTO> dtoList = service.getListMultiFmEdu(yr);

        for (MulticulturalFmDTO dto : dtoList) {
            System.out.println(dto);
        }

    }



}