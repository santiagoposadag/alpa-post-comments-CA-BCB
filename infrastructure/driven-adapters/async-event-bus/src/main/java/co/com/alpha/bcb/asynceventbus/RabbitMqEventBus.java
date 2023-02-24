package co.com.alpha.bcb.asynceventbus;


import co.com.alpha.bcb.asynceventbus.data.Notification;
import co.com.alpha.bcb.model.post.generic.DomainEvent;
import co.com.alpha.bcb.serializer.JSONMapper;
import co.com.alpha.bcb.usecase.generic.gateways.EventBus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventBus implements EventBus {

    public static final String EXCHANGE = "core-posts-events";
    public static final String ROUTING_KEY = "events.routing.key";
    private final RabbitTemplate rabbitTemplate;
    private final JSONMapper serializer;


    public RabbitMqEventBus(RabbitTemplate rabbitTemplate,  JSONMapper serializer) {
        this.serializer = serializer;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent event){
        var notification = new Notification(
                event.getClass().getTypeName(),
                serializer.writeToJson(event)
        );
        rabbitTemplate.convertAndSend(
                this.EXCHANGE, this.ROUTING_KEY, notification.serialize().getBytes()
        );
    }

    @Override
    public void publishError(Throwable errorEvent){
    }
}
