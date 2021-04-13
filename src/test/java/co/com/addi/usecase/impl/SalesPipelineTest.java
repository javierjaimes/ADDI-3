package co.com.addi.usecase.impl;

import co.com.addi.domain.dto.ClientDTO;
import co.com.addi.domain.entities.Client;
import co.com.addi.domain.entities.Prospect;
import co.com.addi.domain.repositories.INationalArchives;
import co.com.addi.domain.repositories.IRegistryNational;
import co.com.addi.infrastructure.crm.CrmSystem;
import co.com.addi.portinterface.impl.PortSalesPipeline;
import co.com.addi.usecase.mapper.MapperSalesPipeline;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SalesPipelineTest {

    private SalesPipeline salesPipeline;

    @Mock
    private MapperSalesPipeline mapperSalesPipeline;

    @Mock
    private IRegistryNational registryNational;

    @Mock
    private INationalArchives nationalArchives;

    @Mock
    private CrmSystem crmSystem;

    @Mock
    private Prospect prospect;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        salesPipeline = new SalesPipeline(mapperSalesPipeline, registryNational, nationalArchives, crmSystem, prospect);

    }

    @Test
    public void validateLeadsStageToProspectsStageTrueTest(){
        Mockito.when(mapperSalesPipeline.clientDTOtoEntityClient(Mockito.any())).thenReturn(getClient());
        Mockito.when(prospect.leadExitsInRegistryNational(Mockito.any())).thenReturn(true);
        Mockito.when(prospect.leadHasJudicialRecords(Mockito.any())).thenReturn(true);
        Mockito.when(prospect.scoreForClient(Mockito.any(), Mockito.anyBoolean(), Mockito.anyBoolean())).thenReturn(getProspect(true));
        Boolean result = salesPipeline.validateLeadsStageToProspectsStage(getClientDTO());
        Assertions.assertTrue(result);

    }

    @Test
    public void validateLeadsStageToProspectsStageFalseTest(){
        Mockito.when(mapperSalesPipeline.clientDTOtoEntityClient(Mockito.any())).thenReturn(getClient());
        Mockito.when(prospect.leadExitsInRegistryNational(Mockito.any())).thenReturn(true);
        Mockito.when(prospect.leadHasJudicialRecords(Mockito.any())).thenReturn(true);
        Mockito.when(prospect.scoreForClient(Mockito.any(), Mockito.anyBoolean(), Mockito.anyBoolean())).thenReturn(getProspect(false));
        Boolean result = salesPipeline.validateLeadsStageToProspectsStage(getClientDTO());
        Assertions.assertFalse(result);

    }


    private ClientDTO getClientDTO(){
        return ClientDTO.builder().name("Test").numberIdentification(12345678L).build();
    }

    private Prospect getProspect(boolean approved){
        int score = 100;
        score = approved ? 80 : 40;
        return Prospect.builder().client(getClient()).exitsInRegistryNational(true).hasJudicialRecords(true).score(score).build();
    }

    private Client getClient(){
        return Client.builder().name("Test").numberIdentification(12345678).build();
    }

}
