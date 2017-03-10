package com.example.nur.derplist.model;

/**
 * Created by Nur on 2/7/2017.
 */

public class Inspiration {

    private int id;
    private String name;
    private String content;
    private boolean bookmark;

    public Inspiration() {
    }

    public Inspiration(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Inspiration(String name, String content, boolean bookmark) {
        this.bookmark = bookmark;
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }
}
