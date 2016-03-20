package com.mforn.data.backend.model.response;

/**
 * POJO of Character
 */
public class CharacterRequest {
    private long id;
    private String name;
    private Thumbnail thumbnail;


    public CharacterRequest() {
    }

    public CharacterRequest(long id, String name, Thumbnail thumbnail) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
