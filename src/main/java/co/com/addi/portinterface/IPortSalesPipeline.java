package co.com.addi.portinterface;

public interface IPortSalesPipeline {
    Boolean validateLeadsStageToProspectsStage(String clientName, Long numberIdentification);
}
