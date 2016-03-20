package com.mforn.data.backend.model.response;

import java.util.List;

/**
 * POJO Character collection data
 */
public class CharacterListData {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private List<CharacterRequest> results;

    public CharacterListData() {
    }

    public CharacterListData(int offset, int limit, int total, int count, List<CharacterRequest> results) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.count = count;
        this.results = results;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CharacterRequest> getResults() {
        return results;
    }

    public void setResults(List<CharacterRequest> results) {
        this.results = results;
    }
}
