package com.mforn.marvel.mapper;

import com.mforn.domain.model.Character;
import com.mforn.domain.model.CharacterDetail;
import com.mforn.marvel.model.CharacterDetailModel;
import com.mforn.marvel.model.CharacterModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper to convert domain data layer to presentation data layer.
 */
public class CharacterModelDataMapper {

    @Inject
    public CharacterModelDataMapper() {
    }

    public List<CharacterModel> transform(List<Character> characterList) {
        List<CharacterModel> characterModelList = new ArrayList<>();

        if (characterList == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        for (Character item : characterList) {
            CharacterModel characterModel = new CharacterModel();
            characterModel.setId(item.getId());
            characterModel.setName(item.getName());
            characterModel.setPortraitImageUrl(item.getPortraitImageUrl());
            characterModel.setFavorite(item.isFavorite());

            characterModelList.add(characterModel);
        }

        return characterModelList;
    }

    public CharacterDetailModel transform(CharacterDetail characterDetail) {
        if (characterDetail == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        CharacterDetailModel characterDetailModel = new CharacterDetailModel();
        characterDetailModel.setId(characterDetail.getId());
        characterDetailModel.setName(characterDetail.getName());
        characterDetailModel.setDescription(characterDetail.getDescription());
        characterDetailModel.setLandscapeImageUrl(characterDetail.getLandscapeImageUrl());
        characterDetailModel.setFavorite(characterDetail.isFavorite());

        return characterDetailModel;
    }
}
