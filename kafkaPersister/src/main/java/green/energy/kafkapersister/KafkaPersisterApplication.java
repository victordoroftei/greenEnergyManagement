package green.energy.kafkapersister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KafkaPersisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaPersisterApplication.class, args);
    }

}
