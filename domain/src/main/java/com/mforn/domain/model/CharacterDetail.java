package com.mforn.domain.model;

/**
 * Domain layer Character detail POJO
 */
public class CharacterDetail {
    private long id;
    private String name;
    private String description;
    private String portraitImageUrl;
    private String landscapeImageUrl;
    private boolean isFavorite;


    public CharacterDetail() {
    }

    public CharacterDetail(long id, String name, String description, String portraitImageUrl, String landscapeImageUrl, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.portraitImageUrl = portraitImageUrl;
        this.landscapeImageUrl = landscapeImageUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLandscapeImageUrl() {
        return landscapeImageUrl;
    }

    public void setLandscapeImageUrl(String landscapeImageUrl) {
        this.landscapeImageUrl = landscapeImageUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getPortraitImageUrl() {
        return portraitImageUrl;
    }

    public void setPortraitImageUrl(String portraitImageUrl) {
        this.portraitImageUrl = portraitImageUrl;
    }
}