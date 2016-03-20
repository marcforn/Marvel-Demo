package com.mforn.marvel.model;

/**
 * Marvel Character detail model layer representation.
 */
public class CharacterDetailModel {
    private long id;
    private String name;
    private String description;
    private String landscapeImageUrl;
    private boolean isFavorite;


    public CharacterDetailModel() {
    }

    public CharacterDetailModel(long id, String name, String description, String landscapeImageUrl, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}