package com.mlj.events;

public abstract class Event {

    private final String id;

    protected Event(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
