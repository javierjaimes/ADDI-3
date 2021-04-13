package co.com.addi.domain.entities;

import co.com.addi.domain.repositories.INationalArchives;
import co.com.addi.domain.repositories.IRegistryNational;
import co.com.addi.infrastructure.crm.CrmSystem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class Prospect implements IProspect{
    private Client client;
    private Boolean exitsInRegistryNational;
    private Boolean hasJudicialRecords;
    private Integer score;

    @Override
    public Boolean leadExitsInRegistryNational(IRegistryNational registryNational){
        return registryNational.validateClient(client);
    }

    @Override
    public Boolean leadHasJudicialRecords(INationalArchives nationalArchives){
        return nationalArchives.clientHasJudicialRecords(client);
    }

    @Override
    public Prospect scoreForClient(CrmSystem crmSystem, Boolean exitsInRegistryNational, Boolean hasJudicialRecords){
        Integer calculateScore = crmSystem.calculateScore(client, exitsInRegistryNational, hasJudicialRecords);

        return Prospect.builder().exitsInRegistryNational(exitsInRegistryNational)
                .hasJudicialRecords(hasJudicialRecords)
                .score(calculateScore).build();
    }

}

