package co.com.alpha.bcb.model.post.values;


import co.com.alpha.bcb.model.post.generic.ValueObject;

public class Content implements ValueObject<String> {

    private String content;


    public Content(String content) {
        this.content = content;
    }

    @Override
    public String value() {
        return content;
    }
}
