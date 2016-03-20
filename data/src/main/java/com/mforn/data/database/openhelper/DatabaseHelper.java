package com.mforn.data.database.openhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mforn.data.database.model.CharacterTable;

import java.sql.SQLException;

/**
 * Database Helper class to handle all database connections
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<CharacterTable, Integer> characterDao;

    /**
     * Class constructor
     *
     * @param context application context (provided from injection)
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Actions to perform when first database connection stablished
     *
     * @param db               current database
     * @param connectionSource current connection
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, CharacterTable.class);
        } catch (SQLException e) {
            Log.e("DatabaseHelper", e.getMessage());
        }
    }

    /**
     * Actions to perform when database is upgraded
     *
     * @param db               current database
     * @param connectionSource current connection
     * @param oldVersion       old database version
     * @param newVersion       new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
    }

    /**
     * Get Ormlite Dao to perform character database request
     *
     * @return Dao<CharacterTable>
     * @throws SQLException
     */
    public Dao<CharacterTable, Integer> getCharacterDao() throws SQLException {
        if (characterDao == null) {
            characterDao = getDao(CharacterTable.class);
        }
        return characterDao;
    }

    /**
     * Close Ormlite Dao when application is destroyed
     */
    @Override
    public void close() {
        super.close();
        characterDao = null;
    }
}