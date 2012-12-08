package com.mlj.views;

public class Link {

    private final String rel;
    private final String uri;

    public Link(String rel, String uri) {
        this.rel = rel;
        this.uri = uri;
    }

    public String getRel() {
        return rel;
    }

    public String getUri() {
        return uri;
    }

}
