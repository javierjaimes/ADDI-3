package co.com.addi.domain.repositories;

import co.com.addi.domain.entities.Client;

public interface QualificationSystem {
    Integer calculateScore(Client client, Boolean exitsInRegistryNational, Boolean hasJudicialRecord);
}
