package Project.Projectspring.chatex.Chatting.Config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class SocketConfig {
    private final String QUEUE_NAME = "chat";
    private final String CHAT_EXCHANGE_NAME = "chat-exchange";
    private final String ROUTING_KEY = "room.*";

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    //topic exchange : chat-exchange
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(CHAT_EXCHANGE_NAME);
    }

    //topic exchange : chat-exchange -> room.* -> chat
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    Binding binding2(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("/topic/messages");
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//        rabbitTemplate.setRoutingKey(QUEUE_NAME);

        return rabbitTemplate;
    }


    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("faily1234");
        factory.setPassword("1234");
        return factory;
    }

    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        objectMapper.registerModule(dateTimeModule());

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);
        return converter;
    }

    @Bean
    Module dateTimeModule() {
        return new JavaTimeModule();
    }
}
