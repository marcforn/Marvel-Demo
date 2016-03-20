package com.mforn.data.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Database table to store favorite characters
 */
@DatabaseTable(tableName = "character")
public class CharacterTable {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_URL_PORTRAIT = "imageUrlPortrait";
    public static final String IMAGE_URL_LANDSCAPE = "imageUrlLandscape";

    @DatabaseField(columnName = ID, id = true, unique = true)
    private Long id;

    @DatabaseField(columnName = NAME)
    private String name;

    @DatabaseField(columnName = DESCRIPTION)
    private String description;

    @DatabaseField(columnName = IMAGE_URL_PORTRAIT)
    private String imageUrlPortrait;

    @DatabaseField(columnName = IMAGE_URL_LANDSCAPE)
    private String imageUrlLandscape;


    public CharacterTable() {
    }

    public CharacterTable(Long id, String name, String description, String imageUrlPortrait, String imageUrlLandscape) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrlPortrait = imageUrlPortrait;
        this.imageUrlLandscape = imageUrlLandscape;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getImageUrlPortrait() {
        return imageUrlPortrait;
    }

    public void setImageUrlPortrait(String imageUrlPortrait) {
        this.imageUrlPortrait = imageUrlPortrait;
    }

    public String getImageUrlLandscape() {
        return imageUrlLandscape;
    }

    public void setImageUrlLandscape(String imageUrlLandscape) {
        this.imageUrlLandscape = imageUrlLandscape;
    }
}