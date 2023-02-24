package co.com.alpha.bcb.model.post.events;


import co.com.alpha.bcb.model.post.generic.DomainEvent;

public class CommentAdded extends DomainEvent {

    private String id;
    private String author;
    private String content;

    public CommentAdded(){
        super("posada.santiago.commentcreated");
    }

    public CommentAdded(String id, String author, String content) {
        super("posada.santiago.commentcreated");
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
