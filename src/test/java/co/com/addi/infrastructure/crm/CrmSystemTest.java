package co.com.addi.infrastructure.crm;

import co.com.addi.domain.entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CrmSystemTest {

    CrmSystem crmSystem;

    @BeforeEach
    public void init(){
        crmSystem = new CrmSystem();
    }

    @Test
    public void calculateLowestScoreWithBothConditionsInTrueTest(){
        Integer result = crmSystem.calculateScore(getClient(), false, true);
        Assertions.assertTrue(result < 60);
        Assertions.assertTrue(result >= 0);
    }

    @Test
    public void calculateLowestScoreWithFirstConditionInTrueTest(){
        Integer result = crmSystem.calculateScore(getClient(), false, false);
        Assertions.assertTrue(result < 60);
        Assertions.assertTrue(result >= 0);
    }

    @Test
    public void calculateLowestScoreWithSecondConditionInTrueTest(){
        Integer result = crmSystem.calculateScore(getClient(), false, true);
        Assertions.assertTrue(result < 60);
        Assertions.assertTrue(result >= 0);
    }

    @Test
    public void calculateHighScoreTest(){
        Integer result = crmSystem.calculateScore(getClient(), true, false);
        Assertions.assertTrue(result > 60);
        Assertions.assertTrue(result <= 100);
    }


    private Client getClient(){
        return Client.builder().name("Test").numberIdentification(12345678).build();
    }


}
