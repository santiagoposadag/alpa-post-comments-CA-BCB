package co.com.alpha.bcb.usecase.generic.gateways;


import co.com.alpha.bcb.model.post.generic.DomainEvent;

public interface EventBus {
    void publish(DomainEvent event);

    void publishError(Throwable errorEvent);
}
