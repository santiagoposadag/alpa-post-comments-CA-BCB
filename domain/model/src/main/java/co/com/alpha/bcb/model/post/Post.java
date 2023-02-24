package co.com.alpha.bcb.model.post;



import co.com.alpha.bcb.model.post.events.CommentAdded;
import co.com.alpha.bcb.model.post.events.PostCreated;
import co.com.alpha.bcb.model.post.generic.AggregateRoot;
import co.com.alpha.bcb.model.post.generic.DomainEvent;
import co.com.alpha.bcb.model.post.values.*;

import java.util.List;
import java.util.Objects;

public class Post extends AggregateRoot<PostId> {

    protected Title title;

    protected Author author;

    protected List<Comment> comments;

    public Post(PostId entityId,
                Title title,
                Author author,
                CommentId commentId,
                Content comment,
                Author commentAuthor) {
        super(entityId);
        subscribe(new PostChange(this));
        appendChange(new PostCreated(title.value(), author.value(), commentId.value(), comment.value(), commentAuthor.value())).apply();
    }

    private Post(PostId id){
        super(id);
        subscribe(new PostChange(this));
    }

    public static Post from(PostId id, List<DomainEvent> events){
        Post post = new Post(id);
        events.forEach(event -> post.applyEvent(event));
        return post;
    }

    public void addAComment(CommentId id, Author author, Content content){
        Objects.requireNonNull(id);
        Objects.requireNonNull(author);
        Objects.requireNonNull(content);
        appendChange(new CommentAdded(id.value(), author.value(), content.value())).apply();
    }
}
