package com.mforn.domain.repository;


import com.mforn.domain.model.Character;
import com.mforn.domain.model.CharacterDetail;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting Backend related data.
 */
public interface BackendRepository {

    Observable<List<Character>> getCharacters(int page);

    Observable<CharacterDetail> getCharacterDetail(long characterId);
}
