package co.com.addi.usecase.impl;

import co.com.addi.domain.dto.ClientDTO;
import co.com.addi.domain.entities.Client;
import co.com.addi.domain.entities.Prospect;
import co.com.addi.domain.repositories.NationalArchives;
import co.com.addi.domain.repositories.RegistryNational;
import co.com.addi.infrastructure.crm.CrmSystem;
import co.com.addi.usecase.ISalesPipeline;
import co.com.addi.usecase.mapper.MapperSalesPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SalesPipeline implements ISalesPipeline {

    private MapperSalesPipeline mapperSalesPipeline;

    private RegistryNational registryNational;
    private NationalArchives nationalArchives;
    private CrmSystem crmSystem;
    private Prospect prospect;

    @Autowired
    public SalesPipeline(MapperSalesPipeline mapperSalesPipeline, RegistryNational registryNational, NationalArchives nationalArchives, CrmSystem crmSystem, Prospect prospect) {
        this.mapperSalesPipeline = mapperSalesPipeline;
        this.registryNational = registryNational;
        this.nationalArchives = nationalArchives;
        this.crmSystem = crmSystem;
        this.prospect = prospect;
    }

    @Override
    public Boolean validateLeadsStageToProspectsStage(ClientDTO clientDTO) {
        Client entityClient = mapperSalesPipeline.clientDTOtoEntityClient(clientDTO);

        prospect.builder().client(entityClient).build();

        CompletableFuture<Boolean> existsInRegistryNationalFuture
                = CompletableFuture.supplyAsync(() -> prospect.leadExitsInRegistryNational(registryNational));

        CompletableFuture<Boolean> hasJudicialRecordsFuture
                = CompletableFuture.supplyAsync(() -> prospect.leadHasJudicialRecords(nationalArchives));

        try {
            Boolean exitsInRegistryNational = existsInRegistryNationalFuture.get();
            Boolean hasJudicialRecords = hasJudicialRecordsFuture.get();
            Prospect prospectAfterValidation = prospect.scoreForClient(crmSystem, exitsInRegistryNational, hasJudicialRecords);
            log.info("Lead Score: " + prospectAfterValidation.getScore());
            if (prospectAfterValidation.getScore() >= 60){
                return Boolean.TRUE;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
