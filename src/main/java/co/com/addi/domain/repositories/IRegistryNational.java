package co.com.addi.domain.repositories;

import co.com.addi.domain.entities.Client;

public interface IRegistryNational {
    boolean validateClient(Client client);
}
