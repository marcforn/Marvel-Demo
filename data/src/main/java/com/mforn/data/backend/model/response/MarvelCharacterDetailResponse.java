package com.mforn.data.backend.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Pojo Character detail response layer
 */
public class MarvelCharacterDetailResponse extends MarvelResponse {

    @SerializedName("data")
    private CharacterDetailData characterListData;

    public MarvelCharacterDetailResponse(CharacterDetailData characterListData) {
        this.characterListData = characterListData;
    }

    public MarvelCharacterDetailResponse(int code, String status, CharacterDetailData characterListData) {
        super(code, status);
        this.characterListData = characterListData;
    }

    public CharacterDetailData getCharacterListData() {
        return characterListData;
    }

    public void setCharacterListData(CharacterDetailData characterListData) {
        this.characterListData = characterListData;
    }
}