package clamos.io.dashboard.multiDropoutStatus.service;

import clamos.io.dashboard.multiDropoutStatus.dto.HighMultiDropoutDTO;

import java.util.List;

public interface HighMultiDropoutService {

    List<HighMultiDropoutDTO> getLocalHighDropoutList(String year);

    List<HighMultiDropoutDTO> getEduHighDropoutList(String year);

}
