package co.com.alpha.bcb.asynceventhandler;

import co.com.alpha.bcb.asynceventhandler.data.Notification;
import co.com.alpha.bcb.model.post.Post;
import co.com.alpha.bcb.model.post.events.PostCreated;
import co.com.alpha.bcb.serializer.JSONMapper;
import co.com.alpha.bcb.serializer.JSONMapperImpl;
import co.com.alpha.bcb.usecase.addcommentevent.AddCommentEventUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class RabbitMqEventHandler {

    public static final String EVENTS_QUEUE = "events.queue";

    private final Logger logger = Logger.getLogger("RabbitMqEventHandler");
    private final JSONMapper mapper = new JSONMapperImpl();

    private final AddCommentEventUseCase useCase;

    public RabbitMqEventHandler(AddCommentEventUseCase useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(queues = EVENTS_QUEUE)
    public void listener(String message) throws ClassNotFoundException {
        Notification notification = Notification.from(message);
        if(notification.getType()
                .equals("co.com.alpha.bcb.model.post.events.PostCreated")){
            logger.info(notification.toString());
            this.useCase.apply(Mono
                    .just((PostCreated) mapper.readFromJson(notification.getBody(),
                            PostCreated.class)))
                    .subscribe();
        }else{
            logger.info("we currently don't have a listener for that event " +notification.toString());
        }
    }
}
