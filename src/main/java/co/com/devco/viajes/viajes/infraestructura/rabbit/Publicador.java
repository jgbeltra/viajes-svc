package co.com.devco.viajes.viajes.infraestructura.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CompletableFuture;

public class Publicador {
    ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConf.class);
    RabbitTemplate template = context.getBean(RabbitTemplate.class);

    public void publicarMensaje(String exchange,  String routingKey, String mensaje){
        template.convertAndSend(exchange, routingKey, mensaje);
    }

    public void publicarMensajeAsincrono(String exchange,  String routingKey, String mensaje){
        CompletableFuture.runAsync(()-> template.convertAndSend(exchange, routingKey, mensaje));
    }
}
