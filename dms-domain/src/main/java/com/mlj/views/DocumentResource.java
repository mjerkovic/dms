package com.mlj.views;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class DocumentResource {

    private final DocumentDto document;
    private final Set<Link> links;

    public DocumentResource(DocumentDto document, Set<Link> links) {
        this.document = document;
        this.links = newHashSet(links);
    }

    public DocumentDto getDocument() {
        return document;
    }

    public Set<Link> getLinks() {
        return links;
    }

}
