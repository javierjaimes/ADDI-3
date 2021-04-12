package co.com.addi.usecase;

import co.com.addi.domain.dto.ClientDTO;

public interface ISalesPipeline {
    Boolean validateLeadsStageToProspectsStage(ClientDTO clientDTO);
}
