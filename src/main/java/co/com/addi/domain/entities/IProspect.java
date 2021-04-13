package co.com.addi.domain.entities;

import co.com.addi.domain.repositories.INationalArchives;
import co.com.addi.domain.repositories.IRegistryNational;
import co.com.addi.infrastructure.crm.CrmSystem;

public interface IProspect {


    Boolean leadExitsInRegistryNational(IRegistryNational registryNational);

    Boolean leadHasJudicialRecords(INationalArchives nationalArchives);

    Prospect scoreForClient(CrmSystem crmSystem, Boolean exitsInRegistryNational, Boolean hasJudicialRecords);
}
