package co.com.alpha.bcb.model.post;


import co.com.alpha.bcb.model.post.generic.Entity;
import co.com.alpha.bcb.model.post.values.Author;
import co.com.alpha.bcb.model.post.values.CommentId;
import co.com.alpha.bcb.model.post.values.Content;

public class Comment extends Entity<CommentId> {

    private Author author;
    private Content content;


    public Comment(CommentId entityId, Author author, Content content) {
        super(entityId);
        this.author = author;
        this.content = content;
    }

    public Author author() {
        return author;
    }

    public Content content() {
        return content;
    }
}
