package co.com.addi.domain.repositories;

import co.com.addi.domain.entities.Client;

public interface NationalArchives {
    boolean clientHasJudicialRecords(Client client);
}
