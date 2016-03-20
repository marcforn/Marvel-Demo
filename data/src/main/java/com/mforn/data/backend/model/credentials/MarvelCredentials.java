package com.mforn.data.backend.model.credentials;

/**
 * Handle Marvel API authentication params
 */
public class MarvelCredentials {
    private String timestamp;
    private String apiKey;
    private String hash;

    public MarvelCredentials() {
    }

    /**
     * Constructor
     *
     * @param timestamp current timestamp
     * @param apiKey    public key
     * @param hash      result of md5(timestamp + privateKey + apikey)
     */
    public MarvelCredentials(String timestamp, String apiKey, String hash) {
        this.timestamp = timestamp;
        this.apiKey = apiKey;
        this.hash = hash;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
