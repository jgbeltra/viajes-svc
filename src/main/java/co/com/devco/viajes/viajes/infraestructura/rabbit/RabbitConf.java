package co.com.devco.viajes.viajes.infraestructura.rabbit;


import lombok.Setter;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("rabbit")
public class RabbitConf {

    @Setter
    private String hostname;
    @Setter
    private int port;

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");

        connectionFactory.setPort(5672);
        connectionFactory.setUsername("viaje");
        connectionFactory.setPassword("viaje");
        connectionFactory.setChannelCheckoutTimeout(10000);
        connectionFactory.setRequestedHeartBeat(3_000);

        return connectionFactory;
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }
}
