package green.energy.rabbitaggregator.service;

import green.energy.rabbitaggregator.model.MeasurementMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitConsumerService {

    private final MeasurementSummaryService measurementSummaryService;

    @RabbitListener(queues = "${spring.rabbitmq.consumer.queue}")
    public void listen(MeasurementMessage message) {
        log.info("Consuming message: {}", message);
        measurementSummaryService.processMessage(message);
    }
}
