package clamos.io.dashboard.multiDropoutStatus.service;

import clamos.io.dashboard.multiDropoutStatus.dto.EleMultiDropoutDTO;

import java.util.List;

public interface EleMultiDropoutService {

    List<EleMultiDropoutDTO> getLocalElementDropoutList(String year);

    List<EleMultiDropoutDTO> getEduElementDropoutList(String year);

}
