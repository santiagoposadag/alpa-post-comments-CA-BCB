package co.com.alpha.bcb.usecase.addcomment;


import co.com.alpha.bcb.model.post.Post;
import co.com.alpha.bcb.model.post.generic.DomainEvent;
import co.com.alpha.bcb.model.post.values.Author;
import co.com.alpha.bcb.model.post.values.CommentId;
import co.com.alpha.bcb.model.post.values.Content;
import co.com.alpha.bcb.model.post.values.PostId;
import co.com.alpha.bcb.usecase.generic.UseCaseForCommand;
import co.com.alpha.bcb.usecase.generic.commands.AddCommentCommand;
import co.com.alpha.bcb.usecase.generic.gateways.DomainEventRepository;
import co.com.alpha.bcb.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AddCommentUseCase extends UseCaseForCommand<AddCommentCommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;

    public AddCommentUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AddCommentCommand> addCommentCommandMono) {
        return addCommentCommandMono.flatMapMany(command -> repository.findById(command.getPostId())
                .collectList()
                .flatMapIterable(events -> {
                    Post post = Post.from(PostId.of(command.getPostId()), events);

                    post.addAComment(CommentId.of(command.getCommentId()),
                            new Author(command.getAuthor()),
                            new Content(command.getContent()));

                    return post.getUncommittedChanges();
                }).map(event -> {
                        bus.publish(event);
                    return event;
                }).flatMap(event -> {
                        return repository.saveEvent(event);
                })
        );

    }
}
