package com.mlj.views;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class DocumentResource {

    private final DocumentDto document;
    private final Map<String, Link> links;

    public DocumentResource(DocumentDto document, Map<String, Link> links) {
        this.document = document;
        this.links = Maps.newHashMap(links);
    }

    public DocumentDto getDocument() {
        return document;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

}
