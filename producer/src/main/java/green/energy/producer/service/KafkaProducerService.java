package green.energy.producer.service;

import green.energy.producer.model.MeasurementMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    @Value("${spring.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, MeasurementMessage> kafkaTemplate;

    private LocalDateTime time = LocalDateTime.of(2024, 1, 1, 0, 0, 0);

    @PostConstruct
    public void init() {
        // Since my timezone is EET (UTC+2/3, depending on daylight savings),
        // it needs to be set as UTC in order to avoid dates being 2/3 hours behind.
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public void sendMessage(MeasurementMessage message) {
        log.info("Posting Message to Kafka: {}", message);

        kafkaTemplate.send(topic, message);
    }

    @Scheduled(fixedRate = 1_000L)
    public void sendMessageFast() {
        // We'll assume the user has solar panels on their house, as well as an inverter, which allows
        // them to "put" energy back on the grid, thus their consumption being negative.

        Long quantity = getRandomLongInInterval(-4_000L, 10_001L);

        int duration = 1;
        MeasurementMessage newMeasurementMessage = buildMeasurementMessage("vic", quantity, time, duration);

        sendMessage(newMeasurementMessage);

        time = time.plusHours(duration);
    }

    @Scheduled(fixedRate = 12_000L)
    public void sendMessageSlow() {
        Long quantity = getRandomLongInInterval(-48_000L, 120_001L);

        int duration = 12;
        MeasurementMessage newMeasurementMessage = buildMeasurementMessage("lavi", quantity, time, duration);

        sendMessage(newMeasurementMessage);

        time = time.plusHours(duration);
    }

    private Long getRandomLongInInterval(Long lowerBound, Long upperBound) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        return random.nextLong(lowerBound, upperBound);  // Energy consumption in Wh
    }

    private MeasurementMessage buildMeasurementMessage(String username, Long quantity, LocalDateTime begin, int duration) {
        return MeasurementMessage.builder()
                .username(username)
                .quantity(quantity)
                .beginTimestamp(begin)
                .endTimestamp(begin.plusHours(duration).minusSeconds(1))
                .build();
    }
}
