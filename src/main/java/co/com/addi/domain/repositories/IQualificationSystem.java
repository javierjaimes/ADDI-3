package co.com.addi.domain.repositories;

import co.com.addi.domain.entities.Client;

public interface IQualificationSystem {
    Integer calculateScore(Client client, Boolean exitsInRegistryNational, Boolean hasJudicialRecord);
}
