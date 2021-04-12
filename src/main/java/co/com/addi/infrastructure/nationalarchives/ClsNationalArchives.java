package co.com.addi.infrastructure.nationalarchives;

import co.com.addi.domain.entities.Client;
import co.com.addi.domain.repositories.NationalArchives;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class ClsNationalArchives implements NationalArchives {

    // Method to simulate the response if any client has Judicial Records
    // it just return true or false with a random approach
    @Override
    public boolean clientHasJudicialRecords(Client client) {
        //Latency simulation from 1 to 5 seconds
        int sleepTime = ThreadLocalRandom.current().nextInt(1, 5);
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int validation = ThreadLocalRandom.current().nextInt(0, 2);

        boolean validationResult = String.valueOf(validation).equals("1");

        return validationResult;
    }
}
