package com.mforn.data.backend;

import com.mforn.data.backend.mapper.BackendEntityMapper;
import com.mforn.data.backend.model.credentials.MarvelCredentials;
import com.mforn.data.backend.utils.HashUtils;
import com.mforn.domain.model.Character;
import com.mforn.domain.model.CharacterDetail;
import com.mforn.domain.repository.BackendRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Class to handle all Backend connections using Retrofit 2 library.
 */
public class BackendDataSource implements BackendRepository {
    private final BackendApiInterface mBackendApiInterface;
    private final BackendEntityMapper mBackendEntityMapper;


    /**
     * Constructor to create Retrofit 2 adapter
     *
     * @param backendEntityMapper mapper class to convert from data layer to domain layer
     */
    @Inject
    public BackendDataSource(BackendEntityMapper backendEntityMapper) {
        Retrofit marvelApiAdapter = new Retrofit.Builder()
                .baseUrl(BackendConstants.PATH_ROOT)
                .client(this.setOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        this.mBackendApiInterface = marvelApiAdapter.create(BackendApiInterface.class);
        this.mBackendEntityMapper = backendEntityMapper;
    }

    /**
     * Configuration of OkHttpClient
     *
     * @return instance of OkHttpClient
     */
    private OkHttpClient setOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // Logging Okhhtp requests
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BackendConstants.LOG_LEVEL);
        httpClient.addInterceptor(logging);

        // Setting Connection Timeout
        httpClient.connectTimeout(BackendConstants.TIME_OUT_MILLIS, TimeUnit.MILLISECONDS);
        httpClient.writeTimeout(BackendConstants.TIME_OUT_MILLIS, TimeUnit.MILLISECONDS);
        httpClient.readTimeout(BackendConstants.TIME_OUT_MILLIS, TimeUnit.MILLISECONDS);

        return httpClient.build();
    }

    /**
     * Handle dynamic credentials on every backend request
     *
     * @return MarvelCredentials
     */
    private MarvelCredentials getCredentials() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String publicKey = BackendConstants.PUBLIC_KEY;
        String privateKey = BackendConstants.PRIVATE_KEY;

        MarvelCredentials marvelCredentials = new MarvelCredentials();
        marvelCredentials.setTimestamp(timestamp);
        marvelCredentials.setApiKey(publicKey);
        marvelCredentials.setHash(HashUtils.md5(timestamp + privateKey + publicKey));

        return marvelCredentials;
    }

    /**
     * Get Character collection from Marvel API
     *
     * @param page page to retrieve
     * @return collection of characters
     */
    @Override
    public Observable<List<Character>> getCharacters(int page) {
        MarvelCredentials marvelCredentials = this.getCredentials();

        return mBackendApiInterface.getCharacters(
                BackendConstants.LIMIT_CHARACTERS_RETRIEVED,
                page * BackendConstants.LIMIT_CHARACTERS_RETRIEVED,
                marvelCredentials.getTimestamp(),
                marvelCredentials.getApiKey(),
                marvelCredentials.getHash())
                .map(mBackendEntityMapper::transform);
    }

    /**
     * Get Character detail
     *
     * @param characterId specified character id
     * @return CharacterDetail
     */
    @Override
    public Observable<CharacterDetail> getCharacterDetail(long characterId) {
        MarvelCredentials marvelCredentials = this.getCredentials();

        return mBackendApiInterface.getCharacterDetail(
                characterId,
                marvelCredentials.getTimestamp(),
                marvelCredentials.getApiKey(),
                marvelCredentials.getHash())
                .map(mBackendEntityMapper::transform);
    }
}
