package co.com.alpha.bcb.usecase.addcommentevent;


import co.com.alpha.bcb.model.post.Post;
import co.com.alpha.bcb.model.post.events.PostCreated;
import co.com.alpha.bcb.model.post.generic.DomainEvent;
import co.com.alpha.bcb.model.post.values.Author;
import co.com.alpha.bcb.model.post.values.CommentId;
import co.com.alpha.bcb.model.post.values.Content;
import co.com.alpha.bcb.model.post.values.PostId;
import co.com.alpha.bcb.usecase.generic.gateways.DomainEventRepository;
import co.com.alpha.bcb.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public class AddCommentEventUseCase implements Function<Mono<PostCreated>, Flux<DomainEvent>> {

    private DomainEventRepository repository;
    private EventBus bus;

    public AddCommentEventUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<PostCreated> postCreatedMono) {
        return postCreatedMono.flatMapIterable(event -> {
                        Post post = Post.from(PostId.of(event.aggregateRootId()), List.of(event));
                        post.addAComment(CommentId.of(event.getCommentId()),
                                new Author(event.getCommentAuthor()),
                                new Content(event.getComment()));
                        return post.getUncommittedChanges();
                    }).flatMap(domainEvent -> {
                        return repository.saveEvent(domainEvent);
                })
                .map(domainEvent -> {
                        bus.publish(domainEvent);
                    return domainEvent;
                });
    }
}
