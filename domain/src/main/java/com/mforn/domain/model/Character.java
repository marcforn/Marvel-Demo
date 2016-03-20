package com.mforn.domain.model;

/**
 * Domain layer Character POJO
 */
public class Character {
    private long id;
    private String name;
    private String portraitImageUrl;
    private boolean isFavorite;


    public Character() {
    }

    public Character(long id, String name, String portraitImageUrl, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.portraitImageUrl = portraitImageUrl;
        this.isFavorite = isFavorite;
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

    public String getPortraitImageUrl() {
        return portraitImageUrl;
    }

    public void setPortraitImageUrl(String portraitImageUrl) {
        this.portraitImageUrl = portraitImageUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}