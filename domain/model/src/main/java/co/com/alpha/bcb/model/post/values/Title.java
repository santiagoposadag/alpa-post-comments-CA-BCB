package co.com.alpha.bcb.model.post.values;

import co.com.alpha.bcb.model.post.generic.ValueObject;

import java.util.Objects;

public class Title implements ValueObject<String> {

    private String title;

    public Title( String title) {
        if(title.length() <= 9){
            throw new IllegalArgumentException();
        }
        this.title = Objects.requireNonNull(title);
    }

    @Override
    public String value() {
        return title;
    }
}
