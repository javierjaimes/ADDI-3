package co.com.addi.infrastructure.commandline.runner;

import co.com.addi.portinterface.IPortSalesPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private IPortSalesPipeline portSalesPipeline;

    @Override
    public void run(String... args) throws Exception {
      log.info("my message from runner");
        Scanner sc = new Scanner(System.in);
        String name;
        Long number;

        name = scanClientName(sc);
        number = scanClientNumberIdentification(sc);

        log.info("Can the Lead be marked as a prospect: " + portSalesPipeline.validateLeadsStageToProspectsStage(name, number));

    }

    private Long scanClientNumberIdentification(Scanner sc) {
        long number;
        do {
            System.out.println("Please enter an identification number!");
            while (!sc.hasNextLong()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!
            }
            number = sc.nextLong();
        } while (number <= 0);
        System.out.println("Thank you! Got " + number);

        return number;
    }

    private String scanClientName(Scanner sc) {
        String name;
        do {
            System.out.println("Please enter a Client's Name: ");
            name = sc.next();
        } while (name.isEmpty());

        return name;
    }
}
