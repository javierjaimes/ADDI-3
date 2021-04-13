package co.com.addi.portinterface.impl;

import co.com.addi.domain.dto.ClientDTO;
import co.com.addi.usecase.ISalesPipeline;
import co.com.addi.usecase.impl.SalesPipeline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class ClsPortSalesPipelineTest {

    @InjectMocks
    private PortSalesPipeline clsPortSalesPipeline;

    @Mock
    private SalesPipeline salesPipelineUC;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        clsPortSalesPipeline = new PortSalesPipeline(salesPipelineUC);
        ReflectionTestUtils.setField(
                clsPortSalesPipeline, "salesPipelineUC", salesPipelineUC);
    }

    @Test
    public void validateLeadsStageToProspectsStageTrueTest() {
        Mockito.when(salesPipelineUC.validateLeadsStageToProspectsStage(Mockito.any())).thenReturn(true);
        Boolean result = clsPortSalesPipeline.validateLeadsStageToProspectsStage("Test", 123456L);
        Assertions.assertTrue(result);
    }

    @Test
    public void validateLeadsStageToProspectsStageFalseTest() {
        Mockito.when(salesPipelineUC.validateLeadsStageToProspectsStage(Mockito.any())).thenReturn(false);
        Boolean result = clsPortSalesPipeline.validateLeadsStageToProspectsStage("Test", 123456L);
        Assertions.assertFalse(result);
    }


}
