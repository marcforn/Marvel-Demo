package com.mforn.data.backend.model.response;

/**
 * Backend response base class
 */
public class MarvelResponse {
    private int code;
    private String status;

    public MarvelResponse() {
    }

    public MarvelResponse(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
