package co.com.addi.domain.entities;

import co.com.addi.AddiApp;
import co.com.addi.domain.repositories.INationalArchives;
import co.com.addi.domain.repositories.IRegistryNational;
import co.com.addi.infrastructure.crm.CrmSystem;
import co.com.addi.infrastructure.nationalarchives.NationalArchives;
import co.com.addi.infrastructure.nationalregistry.RegisterNational;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class ProspectTest {

    @InjectMocks
    private Prospect prospect;

    @Mock
    private Client client;

    @Mock
    private IRegistryNational registryNational;

    @Mock
    private INationalArchives nationalArchives;

    @Mock
    private CrmSystem crmSystem;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(
                prospect, "client", client);
        client = getClient();

    }

    @Test
    public void leadExitsInRegistryNationalTrueTest(){
        Mockito.when(registryNational.validateClient(Mockito.any())).thenReturn(true);
        Boolean result = registryNational.validateClient(client);
        Assertions.assertTrue(result);
    }

    @Test
    public void leadExitsInRegistryNationalFalseTest(){
        Mockito.when(registryNational.validateClient(Mockito.any())).thenReturn(false);
        Boolean result = registryNational.validateClient(client);
        Assertions.assertFalse(result);
    }

    @Test
    public void leadHasJudicialRecordsTrueTest(){
        Mockito.when(nationalArchives.clientHasJudicialRecords(Mockito.any())).thenReturn(true);
        Boolean result = nationalArchives.clientHasJudicialRecords(client);
        Assertions.assertTrue(result);
    }

    @Test
    public void leadHasJudicialRecordsFalseTest(){
        Mockito.when(nationalArchives.clientHasJudicialRecords(Mockito.any())).thenReturn(false);
        Boolean result = nationalArchives.clientHasJudicialRecords(client);
        Assertions.assertFalse(result);
    }

    @Test
    public void scoreForClientTest(){
        Mockito.when(crmSystem.calculateScore(Mockito.any(), Mockito.anyBoolean(), Mockito.anyBoolean())).thenReturn(getScore());
        Prospect prospect = this.prospect.scoreForClient(crmSystem, true, true);
        Assertions.assertEquals(40, prospect.getScore());
    }

    private Client getClient(){
        return Client.builder().name("Test").numberIdentification(12345678).build();
    }

    private Integer getScore(){
        return 40;
    }
}
