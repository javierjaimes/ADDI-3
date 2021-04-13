package co.com.addi.portinterface.impl;

import co.com.addi.domain.dto.ClientDTO;
import co.com.addi.portinterface.IPortSalesPipeline;
import co.com.addi.usecase.ISalesPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortSalesPipeline implements IPortSalesPipeline {

    private ISalesPipeline salesPipelineUC;

    @Autowired
    public PortSalesPipeline(ISalesPipeline salesPipelineUC) {
        this.salesPipelineUC = salesPipelineUC;
    }

    @Override
    public Boolean validateLeadsStageToProspectsStage(String clientName, Long numberIdentification) {
        ClientDTO clientDTO = new ClientDTO(numberIdentification, clientName);

        return salesPipelineUC.validateLeadsStageToProspectsStage(clientDTO);

    }
}
