package com.mforn.data.database.mapper;

import com.mforn.data.database.model.CharacterTable;
import com.mforn.domain.model.Character;
import com.mforn.domain.model.CharacterDetail;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class to convert backend data from data layer to domain layer.
 */
public class DatabaseEntityMapper {

    @Inject
    public DatabaseEntityMapper() {
    }

    /**
     * Convert collection of characters
     *
     * @param characterTableList data layer
     * @return domain layer
     */
    public List<Character> transform(List<CharacterTable> characterTableList) {
        List<Character> characterList = new ArrayList<>();

        for (CharacterTable item : characterTableList) {
            Character character = new Character();
            character.setId(item.getId());
            character.setName(item.getName());
            character.setPortraitImageUrl(item.getImageUrlPortrait());

            characterList.add(character);
        }

        return characterList;
    }

    /**
     * Convert character detail
     *
     * @param characterTable data layer
     * @return domain layer
     */
    public CharacterDetail transform(CharacterTable characterTable) {
        CharacterDetail characterDetail = new CharacterDetail();
        characterDetail.setId(characterTable.getId());
        characterDetail.setName(characterTable.getName());
        characterDetail.setDescription(characterTable.getDescription());
        characterDetail.setLandscapeImageUrl(characterTable.getImageUrlLandscape());
        characterDetail.setFavorite(true);

        return characterDetail;
    }

    /**
     * Conversion from data model to domain model
     *
     * @param characterDetail domain layer
     * @return data layer
     */
    public CharacterTable convert(CharacterDetail characterDetail) {
        CharacterTable characterTable = new CharacterTable();
        characterTable.setId(characterDetail.getId());
        characterTable.setName(characterDetail.getName());
        characterTable.setDescription(characterDetail.getDescription());
        characterTable.setImageUrlPortrait(characterDetail.getPortraitImageUrl());
        characterTable.setImageUrlLandscape(characterDetail.getLandscapeImageUrl());

        return characterTable;
    }
}
