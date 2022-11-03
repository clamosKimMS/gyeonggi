package clamos.io.dashboard.multiDropoutStatus.service;

import clamos.io.dashboard.multiDropoutStatus.dto.MidMultiDropoutDTO;

import java.util.List;

public interface MidMultiDropoutService {

    List<MidMultiDropoutDTO> getLocalMidDropoutList(String year);

    List<MidMultiDropoutDTO> getEduMidDropoutList(String year);

}
