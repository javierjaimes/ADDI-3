package co.com.addi.infrastructure.crm;

import co.com.addi.domain.entities.Client;
import co.com.addi.domain.repositories.QualificationSystem;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class CrmSystem implements QualificationSystem {

    public static final int LOWEST_SCORE_RANGE = 0;
    public static final int HIGHEST_SCORE_RANGE = 100;
    public static final int UMBRAL_SCORE_RANGE = 60;

    @Override
    public Integer calculateScore(Client client, Boolean exitsInRegistryNational, Boolean hasJudicialRecords) {
        if (!exitsInRegistryNational || hasJudicialRecords){
            return ThreadLocalRandom.current().nextInt(LOWEST_SCORE_RANGE, UMBRAL_SCORE_RANGE);
        } else {
            return ThreadLocalRandom.current().nextInt(UMBRAL_SCORE_RANGE, HIGHEST_SCORE_RANGE);
        }
    }
}
