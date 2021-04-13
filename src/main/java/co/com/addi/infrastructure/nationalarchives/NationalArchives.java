package co.com.addi.infrastructure.nationalarchives;

import co.com.addi.domain.entities.Client;
import co.com.addi.domain.repositories.INationalArchives;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class NationalArchives implements INationalArchives {

    public static final int MINIMUN_SECONDS_SLEEP = 1;
    public static final int MAXIMUN_SECONDS_SLEEP = 5;
    public static final int MINIMUN_RANDOM_VALUE_GENERATE = 0;
    public static final int MAXIMUN_RANDOM_VALUE_GENERATE_EXCLUDED = 2;
    public static final String TRUE_IN_STRING = "1";

    // Method to simulate the response if any client has Judicial Records
    // it just return true or false with a random approach
    @Override
    public boolean clientHasJudicialRecords(Client client) {
        //Latency simulation from 1 to 5 seconds
        int sleepTime = ThreadLocalRandom.current().nextInt(MINIMUN_SECONDS_SLEEP, MAXIMUN_SECONDS_SLEEP);
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int validation = ThreadLocalRandom.current().nextInt(MINIMUN_RANDOM_VALUE_GENERATE, MAXIMUN_RANDOM_VALUE_GENERATE_EXCLUDED);

        boolean validationResult = String.valueOf(validation).equals(TRUE_IN_STRING);

        return validationResult;
    }
}
