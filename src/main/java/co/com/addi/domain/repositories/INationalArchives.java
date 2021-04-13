package co.com.addi.domain.repositories;

import co.com.addi.domain.entities.Client;

public interface INationalArchives {
    boolean clientHasJudicialRecords(Client client);
}
