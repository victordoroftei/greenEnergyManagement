package green.energy.producer.config;

import green.energy.producer.model.MeasurementMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, MeasurementMessage> producerFactory() {
        // Did this in order to avoid sending the package name of Measurement in the headers.
        // If it's sent, the consumer app won't be able to "find" the class in itself.
        JsonSerializer<MeasurementMessage> jsonSerializer = new JsonSerializer<>();
        jsonSerializer.setAddTypeInfo(false);

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), jsonSerializer);
    }

    @Bean
    public KafkaTemplate<String, MeasurementMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
