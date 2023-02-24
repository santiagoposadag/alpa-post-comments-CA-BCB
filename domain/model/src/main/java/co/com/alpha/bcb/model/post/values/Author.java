package co.com.alpha.bcb.model.post.values;


import co.com.alpha.bcb.model.post.generic.ValueObject;

public class Author implements ValueObject<String> {

    private String author;

    public Author(String author) {
        this.author = author;
    }

    @Override
    public String value() {
        return author;
    }
}
