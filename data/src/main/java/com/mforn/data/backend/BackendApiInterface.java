package com.mforn.data.backend;

import com.mforn.data.backend.model.response.MarvelCharacterDetailResponse;
import com.mforn.data.backend.model.response.MarvelCharacterListResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Retrofit 2 interface to handle all Backend requests.
 */
public interface BackendApiInterface {

    @GET(BackendConstants.PATH_GET_CHARACTER_LIST)
    Observable<MarvelCharacterListResponse> getCharacters(
            @Query(BackendConstants.PARAM_LIMIT) int limit,
            @Query(BackendConstants.PARAM_OFFSET) int offset,
            @Query(BackendConstants.PARAM_TS) String timestamp,
            @Query(BackendConstants.PARAM_API_KEY) String apikey,
            @Query(BackendConstants.PARAM_HASH) String hashSignature);

    @GET(BackendConstants.PATH_GET_CHARACTER_DETAIL)
    Observable<MarvelCharacterDetailResponse> getCharacterDetail(
            @Path(BackendConstants.PARAM_CHARACTER_ID) long characterId,
            @Query(BackendConstants.PARAM_TS) String timestamp,
            @Query(BackendConstants.PARAM_API_KEY) String apikey,
            @Query(BackendConstants.PARAM_HASH) String hashSignature);
}
