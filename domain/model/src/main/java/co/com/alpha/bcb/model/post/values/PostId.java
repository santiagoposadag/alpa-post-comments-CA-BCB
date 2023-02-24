package co.com.alpha.bcb.model.post.values;


import co.com.alpha.bcb.model.post.generic.Identity;

public class PostId extends Identity {
    private PostId(String uuid) {
        super(uuid);
    }

    public PostId() {
    }

    public static PostId of(String uuid){
        return new PostId(uuid);
    }
}
