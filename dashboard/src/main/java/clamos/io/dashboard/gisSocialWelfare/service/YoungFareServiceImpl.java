package clamos.io.dashboard.gisSocialWelfare.service;


import clamos.io.dashboard.gisSocialWelfare.dto.YoungFareDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class YoungFareServiceImpl implements YoungFareService{


     
     @Override
     public List<YoungFareDto> getListYoungFare(String Area) {
          return null;
     }

     @Override
     public List<YoungFareDto> getDetailYoungFare() {
          return null;
     }
}
