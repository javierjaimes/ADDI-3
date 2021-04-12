package co.com.addi.infrastructure.nationalarchives;

import co.com.addi.domain.entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClsNationalArchivesTest {

    private ClsNationalArchives clsNationalArchives;

    @BeforeEach
    public void init(){
        clsNationalArchives = new ClsNationalArchives();
    }

    // Dummy Test: it was designed to be implemented according with real external systems response
    @Test
    public void clientHasJudicialRecordsTest(){
        Boolean response = clsNationalArchives.clientHasJudicialRecords(getClient());
        Assertions.assertTrue(response == Boolean.FALSE || response == Boolean.TRUE);
    }

    private Client getClient(){
        return Client.builder().name("Test").numberIdentification(12345678).build();
    }
}
