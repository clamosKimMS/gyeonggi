package clamos.io.dashboard.multiDropoutStatus.service;

import clamos.io.dashboard.multiDropoutStatus.dto.EleMultiDropoutDTO;
import clamos.io.dashboard.multiDropoutStatus.dto.HighMultiDropoutDTO;
import clamos.io.dashboard.multiDropoutStatus.dto.MidMultiDropoutDTO;
import clamos.io.dashboard.multiDropoutStatus.dto.TotalMultiDropoutDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class TotalMultiDropoutServiceImpl implements TotalMultiDropoutService{

    @Autowired
    private EleMultiDropoutService eleService;

    @Autowired
    private MidMultiDropoutService midService;

    @Autowired
    private HighMultiDropoutService highService;

    @Override
    public List<TotalMultiDropoutDTO> getLocalTotalDropoutList(String year) {

        List<EleMultiDropoutDTO> eleList = eleService.getLocalElementDropoutList(year);
        List<MidMultiDropoutDTO> midList = midService.getLocalMidDropoutList(year);
        List<HighMultiDropoutDTO> highList = highService.getLocalHighDropoutList(year);

        List<TotalMultiDropoutDTO> totalList = new ArrayList<>();

        for (int i = 0; i < eleList.size(); i++) {

            TotalMultiDropoutDTO dto_tmp = TotalMultiDropoutDTO.builder()
                    .site_nm(eleList.get(i).getSite_nm())
                    .ppltn_sum_total(eleList.get(i).getPpltn_sum_total() + midList.get(i).getPpltn_sum_total() + highList.get(i).getPpltn_sum_total())
                    .build();

            totalList.add(dto_tmp);

        }

        return totalList;
    }

    @Override
    public Integer getLocalTotalDropoutMax(String year) {
        List<TotalMultiDropoutDTO> dtoList = getLocalTotalDropoutList(year);

        Integer max = 0;

        for (TotalMultiDropoutDTO dto : dtoList) {
            max = dto.getPpltn_sum_total() > max ? dto.getPpltn_sum_total() : max;
        }

        return max;
    }

    @Override
    public List<TotalMultiDropoutDTO> getEduTotalDropoutList(String year) {

        int guri = 0;
        int donducheon = 0;
        int anyang = 0;
        int osan = 0;
        int hanam = 0;
        int uiwang = 0;

        List<EleMultiDropoutDTO> eleList = eleService.getLocalElementDropoutList(year);
        List<MidMultiDropoutDTO> midList = midService.getLocalMidDropoutList(year);
        List<HighMultiDropoutDTO> highList = highService.getLocalHighDropoutList(year);

        List<TotalMultiDropoutDTO> totalList = new ArrayList<>();

        for (int i = 0; i < eleList.size(); i++) {

            TotalMultiDropoutDTO dto_tmp = TotalMultiDropoutDTO.builder()
                    .site_nm(eleList.get(i).getSite_nm())
                    .ppltn_sum_total(eleList.get(i).getPpltn_sum_total() + midList.get(i).getPpltn_sum_total() + highList.get(i).getPpltn_sum_total())
                    .build();

            totalList.add(dto_tmp);

        }

        for (int i = 0; i < totalList.size(); i++) {
            if (totalList.get(i).getSite_nm().equals("구리시")) {
                guri = i;
            }
            if (totalList.get(i).getSite_nm().equals("동두천시")) {
                donducheon = i;
            }
            if (totalList.get(i).getSite_nm().equals("안양시")) {
                anyang = i;
            }
            if (totalList.get(i).getSite_nm().equals("오산시")) {
                osan = i;
            }
            if (totalList.get(i).getSite_nm().equals("하남시")) {
                hanam = i;
            }
            if (totalList.get(i).getSite_nm().equals("의왕시")) {
                uiwang = i;
            }
        }

        for (int i = 0; i < totalList.size(); i++) {
            if (totalList.get(i).getSite_nm().equals("남양주시")) {

                totalList.get(i).setSite_nm("남양주시-구리시");
                totalList.get(i).setPpltn_sum_total(totalList.get(i).getPpltn_sum_total() + totalList.get(guri).getPpltn_sum_total());

            }
            if (totalList.get(i).getSite_nm().equals("양주시")) {

                totalList.get(i).setSite_nm("양주시-동두천시");
                totalList.get(i).setPpltn_sum_total(totalList.get(i).getPpltn_sum_total() + totalList.get(donducheon).getPpltn_sum_total());

            }
            if (totalList.get(i).getSite_nm().equals("과천시")) {

                totalList.get(i).setSite_nm("과천시-안양시");
                totalList.get(i).setPpltn_sum_total(totalList.get(i).getPpltn_sum_total() + totalList.get(anyang).getPpltn_sum_total());

            }
            if (totalList.get(i).getSite_nm().equals("화성시")) {

                totalList.get(i).setSite_nm("화성시-오산시");
                totalList.get(i).setPpltn_sum_total(totalList.get(i).getPpltn_sum_total() + totalList.get(osan).getPpltn_sum_total());

            }
            if (totalList.get(i).getSite_nm().equals("광주시")) {

                totalList.get(i).setSite_nm("광주시-하남시");
                totalList.get(i).setPpltn_sum_total(totalList.get(i).getPpltn_sum_total() + totalList.get(hanam).getPpltn_sum_total());

            }
            if (totalList.get(i).getSite_nm().equals("군포시")) {

                totalList.get(i).setSite_nm("군포시-의왕시");
                totalList.get(i).setPpltn_sum_total(totalList.get(i).getPpltn_sum_total() + totalList.get(uiwang).getPpltn_sum_total());

            }
        }

        for (int i = 0; i < totalList.size(); i++) {

            if (totalList.get(i).getSite_nm().equals("구리시")) {
                totalList.remove(i);
            }
            if (totalList.get(i).getSite_nm().equals("동두천시")) {
                totalList.remove(i);
            }
            if (totalList.get(i).getSite_nm().equals("안양시")) {
                totalList.remove(i);
            }
            if (totalList.get(i).getSite_nm().equals("오산시")) {
                totalList.remove(i);
            }
            if (totalList.get(i).getSite_nm().equals("하남시")) {
                totalList.remove(i);
            }
            if (totalList.get(i).getSite_nm().equals("의왕시")) {
                totalList.remove(i);
            }

        }

        return totalList;

    }

    @Override
    public Integer getEduTotalDropoutMax(String year) {
        List<TotalMultiDropoutDTO> dtoList = getEduTotalDropoutList(year);

        Integer max = 0;

        for (TotalMultiDropoutDTO dto : dtoList) {
            max = dto.getPpltn_sum_total() > max ? dto.getPpltn_sum_total() : max;
        }

        return max;
    }
}
