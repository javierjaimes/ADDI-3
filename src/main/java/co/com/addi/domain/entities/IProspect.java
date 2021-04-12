package co.com.addi.domain.entities;

import co.com.addi.domain.repositories.NationalArchives;
import co.com.addi.domain.repositories.RegistryNational;
import co.com.addi.infrastructure.crm.CrmSystem;

public interface IProspect {


    Boolean leadExitsInRegistryNational(RegistryNational registryNational);

    Boolean leadHasJudicialRecords(NationalArchives nationalArchives);

    Prospect scoreForClient(CrmSystem crmSystem, Boolean exitsInRegistryNational, Boolean hasJudicialRecords);
}
