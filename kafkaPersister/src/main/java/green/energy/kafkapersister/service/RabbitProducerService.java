package green.energy.kafkapersister.service;

import green.energy.kafkapersister.model.MeasurementMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.producer.queue}")
    private String queueName;

    public void publishMessageForSummarizing(MeasurementMessage message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
