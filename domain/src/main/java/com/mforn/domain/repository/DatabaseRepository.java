package com.mforn.domain.repository;


import com.mforn.domain.model.Character;
import com.mforn.domain.model.CharacterDetail;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting Database related data.
 */
public interface DatabaseRepository {

    Observable<CharacterDetail> isCharacterFavorite(CharacterDetail characterDetail);

    Observable<Boolean> toggleFavorite(CharacterDetail characterDetail);

    Observable<List<Character>> getFavoriteCharacterList();

    Observable<CharacterDetail> getFavoriteCharacterDetail(long characterId);
}
