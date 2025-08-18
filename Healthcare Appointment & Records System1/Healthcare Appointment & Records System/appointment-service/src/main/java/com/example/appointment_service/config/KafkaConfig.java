    package com.example.appointment_service.config;

    import org.apache.kafka.clients.admin.NewTopic;
    import org.apache.kafka.clients.producer.ProducerConfig;
    import org.apache.kafka.common.serialization.LongSerializer;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.kafka.config.TopicBuilder;
    import org.springframework.kafka.core.DefaultKafkaProducerFactory;
    import org.springframework.kafka.core.KafkaTemplate;
    import org.springframework.kafka.core.ProducerFactory;
    import org.springframework.kafka.support.serializer.JsonSerializer;
    import com.example.appointment_service.dto.AppointmentEvent;

    import java.util.HashMap;
    import java.util.Map;

    @Configuration
    public class KafkaConfig {

        @Bean
        public NewTopic appointmentConfirmedTopic() {
            return TopicBuilder.name("appointment-confirmed-events")
                    .partitions(1)
                    .replicas(1)
                    .build();
        }

        @Bean
        public ProducerFactory<Long, AppointmentEvent> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public KafkaTemplate<Long, AppointmentEvent> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }
    }
    