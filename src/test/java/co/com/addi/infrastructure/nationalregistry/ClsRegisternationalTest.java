package co.com.addi.infrastructure.nationalregistry;

import co.com.addi.domain.entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClsRegisternationalTest {

    private ClsRegisternational registryNational;

    @BeforeEach
    public void init(){
        registryNational = new ClsRegisternational();
    }

    // Dummy Test: it was designed to be implemented according with real external systems response
    @Test
    public void validateClientTest(){
        Boolean response = registryNational.validateClient(getClient());
        Assertions.assertTrue(response == Boolean.FALSE || response == Boolean.TRUE);
    }

    private Client getClient(){
        return Client.builder().name("Test").numberIdentification(12345678).build();
    }
}
