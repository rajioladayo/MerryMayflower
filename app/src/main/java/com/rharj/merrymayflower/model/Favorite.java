package com.rharj.merrymayflower.model;

/**
 * Created by USER on 4/12/15.
 */
public class Favorite {

    private long id;
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return title;
    }
}
