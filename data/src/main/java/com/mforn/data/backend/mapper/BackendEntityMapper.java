package com.mforn.data.backend.mapper;

import com.mforn.data.backend.model.response.CharacterDetailRequest;
import com.mforn.data.backend.model.response.CharacterRequest;
import com.mforn.data.backend.model.response.MarvelCharacterDetailResponse;
import com.mforn.data.backend.model.response.MarvelCharacterListResponse;
import com.mforn.domain.model.Character;
import com.mforn.domain.model.CharacterDetail;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class to convert backend data from data layer to domain layer.
 */
public class BackendEntityMapper {

    @Inject
    public BackendEntityMapper() {
    }

    /**
     * Convert collection of characters
     *
     * @param marvelCharacterListResponse data layer
     * @return domain layer
     */
    public List<Character> transform(MarvelCharacterListResponse marvelCharacterListResponse) {
        List<Character> characterList = new ArrayList<>();

        List<CharacterRequest> characterRequestList = marvelCharacterListResponse.getCharacterListData().getResults();

        for (CharacterRequest item : characterRequestList) {
            Character character = new Character();
            character.setId(item.getId());
            character.setName(item.getName());
            character.setPortraitImageUrl(item.getThumbnail().getPath().concat("/portrait_xlarge.jpg"));

            characterList.add(character);
        }

        return characterList;
    }

    /**
     * Convert character detail
     *
     * @param marvelCharacterDetailResponse data layer
     * @return doamin layer
     */
    public CharacterDetail transform(MarvelCharacterDetailResponse marvelCharacterDetailResponse) {
        CharacterDetailRequest characterDetailRequest = marvelCharacterDetailResponse.getCharacterListData().getResults().get(0);

        CharacterDetail characterDetail = new CharacterDetail();
        characterDetail.setId(characterDetailRequest.getId());
        characterDetail.setName(characterDetailRequest.getName());
        characterDetail.setDescription(characterDetailRequest.getDescription());
        characterDetail.setPortraitImageUrl(characterDetailRequest.getThumbnail().getPath().concat("/portrait_xlarge.jpg"));
        characterDetail.setLandscapeImageUrl(characterDetailRequest.getThumbnail().getPath().concat("/landscape_xlarge.jpg"));

        return characterDetail;
    }
}
