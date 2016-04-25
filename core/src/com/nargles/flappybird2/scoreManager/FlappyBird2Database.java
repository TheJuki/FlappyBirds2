package com.nargles.flappybird2.scoreManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

import java.util.ArrayList;
import java.util.List;

/**
 * Database Handler
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class FlappyBird2Database {

    private Database dbHandler;

    private static final String TABLE_HIGHSCORES = "highscore";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_HIGHSCORE = "highscore";

    private static final String DATABASE_NAME = "flappybird2.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table if not exists "
            + TABLE_HIGHSCORES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_HIGHSCORE
            + " integer);";

    /**
     * Constructor
     */
    public FlappyBird2Database() {

         dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME,
                DATABASE_VERSION, DATABASE_CREATE, null);

        dbHandler.setupDatabase();
        try {
            dbHandler.openOrCreateDatabase();
            dbHandler.execSQL(DATABASE_CREATE);
        } catch (SQLiteGdxException e) {
            Gdx.app.log("FlappyBirdsDatabase SQL Exception", e.getMessage());
        }

        Gdx.app.log("FlappyBirdsDatabase", "created/opened successfully");

    }

    /**
     * Add new HighScore
     *
     * @param highScore HighScore to add
     */
    public void addHighScore(int highScore) {
        DatabaseCursor cursor = null;

        try {
            cursor = dbHandler.rawQuery("SELECT highscore FROM highscore WHERE highscore = '" + highScore + "'");
        } catch (SQLiteGdxException e) {
            Gdx.app.log("FlappyBirdsDatabase SQL Exception", e.getMessage());
        }

        if (cursor != null && !cursor.next()) {
            try {
                dbHandler
                        .execSQL("INSERT INTO highscore ('highscore') VALUES ('" + highScore + "')");
            } catch (SQLiteGdxException e) {
                Gdx.app.log("FlappyBirdsDatabase SQL Exception", e.getMessage());
            }

            cursor.close();
        }
    }

    /**
     * Get all HighScores as a list
     *
     * @return List of all HighScores
     */
    public List<Integer> getAllHighScores() {
        List<Integer> list = new ArrayList<Integer>();
        DatabaseCursor cursor = null;

        try {
            cursor = dbHandler.rawQuery("SELECT * FROM highscore ORDER BY highscore desc");
        } catch (SQLiteGdxException e) {
            Gdx.app.log("FlappyBirdsDatabase SQL Exception", e.getMessage());
        }
        if (cursor != null) {
            while (cursor.next()) {
                list.add(cursor.getInt(1));
            }

            cursor.close();
        }

        return list;
    }

    /**
     * Get Highest HighScore
     *
     * @return Highest HighScore
     */
    public int getHighestHighScore() {
        int highScore = 0;
        DatabaseCursor cursor = null;

        try {
            cursor = dbHandler.rawQuery("SELECT MAX(highscore) AS HighestScore FROM highscore");
        } catch (SQLiteGdxException e) {
            Gdx.app.log("FlappyBirdsDatabase SQL Exception", e.getMessage());
        }

        if (cursor != null && cursor.next()) {
            highScore = cursor.getInt(0);

            cursor.close();
        }

        return highScore;
    }

    /**
     * Close database
     */
    public void close() {
        if (this.dbHandler != null) {
            try {
                this.dbHandler.closeDatabase();
            } catch (Exception e) {
                Gdx.app.log("FlappyBirdsDatabase SQL Exception", e.getMessage());
            }
            this.dbHandler = null;
            Gdx.app.log("FlappyBirdsDatabase", "disposed");
        }
    }
}