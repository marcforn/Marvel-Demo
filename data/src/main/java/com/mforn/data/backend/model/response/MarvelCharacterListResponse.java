package com.mforn.data.backend.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Pojo Character collection response layer
 */
public class MarvelCharacterListResponse extends MarvelResponse {

    @SerializedName("data")
    private CharacterListData characterListData;

    public MarvelCharacterListResponse(CharacterListData characterListData) {
        this.characterListData = characterListData;
    }

    public MarvelCharacterListResponse(int code, String status, CharacterListData characterListData) {
        super(code, status);
        this.characterListData = characterListData;
    }

    public CharacterListData getCharacterListData() {
        return characterListData;
    }

    public void setCharacterListData(CharacterListData characterListData) {
        this.characterListData = characterListData;
    }
}