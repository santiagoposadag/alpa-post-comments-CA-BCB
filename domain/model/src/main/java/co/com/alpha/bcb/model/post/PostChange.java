package co.com.alpha.bcb.model.post;



import co.com.alpha.bcb.model.post.events.CommentAdded;
import co.com.alpha.bcb.model.post.events.PostCreated;
import co.com.alpha.bcb.model.post.generic.EventChange;
import co.com.alpha.bcb.model.post.values.Author;
import co.com.alpha.bcb.model.post.values.CommentId;
import co.com.alpha.bcb.model.post.values.Content;
import co.com.alpha.bcb.model.post.values.Title;

import java.util.ArrayList;

public class PostChange extends EventChange {

    public PostChange(Post post){

        apply((PostCreated event)-> {
            post.title = new Title(event.getTitle());
            post.author = new Author(event.getAuthor());
            post.comments = new ArrayList<>();
        });

        apply((CommentAdded event)-> {
            Comment comment = new Comment(CommentId.of(event.getId()),
                    new Author(event.getAuthor()),
                    new Content(event.getContent()));
            post.comments.add(comment);
        });

    }
}
