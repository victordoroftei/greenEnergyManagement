package green.energy.kafkapersister.service;

import green.energy.kafkapersister.data.entity.Measurement;
import green.energy.kafkapersister.data.entity.User;
import green.energy.kafkapersister.model.MeasurementMessage;
import green.energy.kafkapersister.model.exception.UserNotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final UserService userService;

    private final MeasurementService measurementService;

    private final RabbitProducerService rabbitProducerService;

    @PostConstruct
    public void init() {
        // Since my timezone is EET (UTC+2/3, depending on daylight savings),
        // it needs to be set as UTC in order to avoid dates being 2/3 hours behind.
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(MeasurementMessage message) {
        log.info("Reading Message: {}", message);

        User user;
        try {
            user = userService.getUserByUsername(message.getUsername());
        } catch (UserNotFoundException e) {
            user = User.builder()
                    .username(message.getUsername())
                    .build();

            userService.addUser(user);
        }

        Measurement measurement = Measurement.builder()
                .user(user)
                .quantity(message.getQuantity())
                .beginTimestamp(message.getBeginTimestamp())
                .endTimestamp(message.getEndTimestamp())
                .build();

        measurementService.addMeasurement(measurement);

        rabbitProducerService.publishMessageForSummarizing(message);
    }
}

