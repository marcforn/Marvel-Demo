package com.mforn.data.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.mforn.data.database.mapper.DatabaseEntityMapper;
import com.mforn.data.database.model.CharacterTable;
import com.mforn.data.database.openhelper.DatabaseHelper;
import com.mforn.domain.model.Character;
import com.mforn.domain.model.CharacterDetail;
import com.mforn.domain.repository.DatabaseRepository;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Class to handle all Database connections.
 */
public class DatabaseDataSource implements DatabaseRepository {
    private final DatabaseHelper mDatabaseHelper;
    private final DatabaseEntityMapper mDatabaseEntityMapper;


    /**
     * Constructor to instantiate objects required to database request
     *
     * @param databaseHelper       Ormlite database helper
     * @param databaseEntityMapper converter from data layer to domain layer
     */
    @Inject
    public DatabaseDataSource(DatabaseHelper databaseHelper, DatabaseEntityMapper databaseEntityMapper) {
        this.mDatabaseHelper = databaseHelper;
        this.mDatabaseEntityMapper = databaseEntityMapper;
    }

    /**
     * Get if a character its favorite or not
     *
     * @param characterDetail current CharacterDetail
     * @return character detail updated (Observable)
     */
    @Override
    public Observable<CharacterDetail> isCharacterFavorite(CharacterDetail characterDetail) {
        return Observable.create(new Observable.OnSubscribe<CharacterDetail>() {
            @Override
            public void call(Subscriber<? super CharacterDetail> subscriber) {
                subscriber.onNext(isCharacterFavoriteQuery(characterDetail));
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Toggle favorite character
     *
     * @param characterDetail current CharacterDetail
     * @return is favorite (Observable)
     */
    @Override
    public Observable<Boolean> toggleFavorite(CharacterDetail characterDetail) {
        try {
            boolean isFavorite;

            isFavorite = isCharacterFavoriteQuery(characterDetail).isFavorite();

            if (!isFavorite) {
                // Insert to favorite table
                insertCharacterQuery(characterDetail);
            } else {
                // Delete from favorite table
                deleteCharacterQuery(characterDetail.getId());
            }

            return Observable.just(!isFavorite);
        } catch (SQLException e) {
            return Observable.error(e);
        }
    }

    /**
     * Get favorite character collection
     *
     * @return List<Character> (Observable)
     */
    @Override
    public Observable<List<Character>> getFavoriteCharacterList() {
        return Observable.create(new Observable.OnSubscribe<List<CharacterTable>>() {
            @Override
            public void call(Subscriber<? super List<CharacterTable>> subscriber) {
                try {
                    subscriber.onNext(getFavoriteCharacterListQuery());
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        }).map(mDatabaseEntityMapper::transform);
    }

    /**
     * Get Character detail
     *
     * @param characterId current character id
     * @return CharacterDetail (Observable)
     */
    @Override
    public Observable<CharacterDetail> getFavoriteCharacterDetail(long characterId) {
        return Observable.create(new Observable.OnSubscribe<CharacterTable>() {
            @Override
            public void call(Subscriber<? super CharacterTable> subscriber) {
                try {
                    subscriber.onNext(getFavoriteCharacterDetailQuery(characterId));
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        }).map(mDatabaseEntityMapper::transform);
    }

    /**
     * Get if a character its favorite or not
     *
     * @param characterDetail current CharacterDetail
     * @return character detail updated
     */
    private CharacterDetail isCharacterFavoriteQuery(CharacterDetail characterDetail) {
        try {
            Dao<CharacterTable, Integer> dao = mDatabaseHelper.getCharacterDao();
            QueryBuilder<CharacterTable, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(CharacterTable.ID, characterDetail.getId());
            List<CharacterTable> result = queryBuilder.query();

            if (result.size() > 0) {
                characterDetail.setFavorite(true);
            }

        } catch (SQLException ignored) {
            characterDetail.setFavorite(false);
        }

        return characterDetail;
    }

    /**
     * Insert character into daatabase
     *
     * @param characterDetail current CharacterDetail
     * @throws SQLException
     */
    private void insertCharacterQuery(CharacterDetail characterDetail) throws SQLException {
        Dao<CharacterTable, Integer> dao = mDatabaseHelper.getCharacterDao();
        dao.createOrUpdate(mDatabaseEntityMapper.convert(characterDetail));
    }

    /**
     * Delete character from database
     *
     * @param characterId character id to delete
     * @throws SQLException
     */
    private void deleteCharacterQuery(long characterId) throws SQLException {
        Dao<CharacterTable, Integer> dao = mDatabaseHelper.getCharacterDao();
        DeleteBuilder<CharacterTable, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(CharacterTable.ID, characterId);

        if (deleteBuilder.delete() > 0) {
            throw new SQLException("Error deleting character");
        }
    }

    /**
     * Retrieve favorite character collection
     *
     * @return List<CharacterTable>
     * @throws SQLException
     */
    private List<CharacterTable> getFavoriteCharacterListQuery() throws SQLException {
        Dao<CharacterTable, Integer> dao = mDatabaseHelper.getCharacterDao();
        QueryBuilder<CharacterTable, Integer> queryBuilder = dao.queryBuilder();
        queryBuilder.orderBy(CharacterTable.NAME, true);

        return queryBuilder.query();
    }

    /**
     * Retrieve Character detail from database
     *
     * @param characterId current character id
     * @return CharacterTable
     * @throws SQLException
     */
    private CharacterTable getFavoriteCharacterDetailQuery(long characterId) throws SQLException {
        CharacterTable characterTable = null;

        Dao<CharacterTable, Integer> dao = mDatabaseHelper.getCharacterDao();
        QueryBuilder queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq(CharacterTable.ID, characterId);
        List result = queryBuilder.query();

        if (result.size() > 0) {
            characterTable = (CharacterTable) result.get(0);
        }

        return characterTable;
    }
}
