package co.com.alpha.bcb.model.post.values;


import co.com.alpha.bcb.model.post.generic.Identity;

public class CommentId extends Identity {
    private CommentId(String uuid) {
        super(uuid);
    }

    public CommentId() {
    }

    public static CommentId of(String uuid){
        return new CommentId(uuid);
    }
}
