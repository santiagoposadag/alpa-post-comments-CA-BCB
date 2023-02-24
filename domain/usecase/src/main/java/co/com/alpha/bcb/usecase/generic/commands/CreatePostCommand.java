package co.com.alpha.bcb.usecase.generic.commands;


import co.com.alpha.bcb.usecase.generic.Command;

public class CreatePostCommand extends Command {
    private String postId;
    private String author;
    private String title;
    private String commentId;
    private String comment;
    private String commentAuthor;

    public CreatePostCommand() {
    }

    public CreatePostCommand(String postId, String author, String title) {
        this.postId = postId;
        this.author = author;
        this.title = title;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
