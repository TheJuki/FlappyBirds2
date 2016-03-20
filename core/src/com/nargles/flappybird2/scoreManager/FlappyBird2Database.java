package com.nargles.flappybird2.scoreManager;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class FlappyBird2Database {

    private Database dbHandler;

    public static final String TABLE_HIGHSCORES = "highscore";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_HIGHSCORE = "highscore";

    private static final String DATABASE_NAME = "flappybird2.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table if not exists "
            + TABLE_HIGHSCORES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_HIGHSCORE
            + " integer);";

    public FlappyBird2Database() {
        Gdx.app.log("FlappyBirdsDatabase", "creation started");
        dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME,
                DATABASE_VERSION, DATABASE_CREATE, null);

        dbHandler.setupDatabase();
        try {
            dbHandler.openOrCreateDatabase();
            dbHandler.execSQL(DATABASE_CREATE);
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }

        Gdx.app.log("FlappyBirdsDatabase", "created/opened successfully");
        
        /*       
        try {
            dbHandler
                    .execSQL("DELETE FROM highscore");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }

        try {
            dbHandler
                    .execSQL("INSERT INTO highscore ('highscore') VALUES ('12')");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        DatabaseCursor cursor = null;

        try {
            cursor = dbHandler.rawQuery("SELECT * FROM highscore");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        while (cursor.next()) {
            Gdx.app.log("FromDb", String.valueOf(cursor.getInt(1)));
        }
        
        cursor.close();
        */
        
    }
    
    public void addHighscore(int highscore)
    {
    	 DatabaseCursor cursor = null;

         try {
             cursor = dbHandler.rawQuery("SELECT highscore FROM highscore WHERE highscore = '" + highscore + "'");
         } catch (SQLiteGdxException e) {
             e.printStackTrace();
         }
         
         if(!cursor.next()) {
        	 try {
                 dbHandler
                         .execSQL("INSERT INTO highscore ('highscore') VALUES ('" + highscore + "')");
             } catch (SQLiteGdxException e) {
                 e.printStackTrace();
             }
         }
         
         cursor.close();
    }
    
    public List<Integer> getAllHighscores()
    {
    	List<Integer> list = new ArrayList<Integer>();
    	 DatabaseCursor cursor = null;

         try {
             cursor = dbHandler.rawQuery("SELECT * FROM highscore ORDER BY highscore desc");
         } catch (SQLiteGdxException e) {
             e.printStackTrace();
         }
         while (cursor.next()) {
             list.add(cursor.getInt(1));
         }
         
         cursor.close();
         
         return list;
    }
    
    public int getHighestHighscore()
    {
    	int highscore = 0;
    	 DatabaseCursor cursor = null;

         try {
             cursor = dbHandler.rawQuery("SELECT MAX(highscore) AS HighestScore FROM highscore");
         } catch (SQLiteGdxException e) {
             e.printStackTrace();
         }
         
         if(cursor.next()) {
        	 highscore = cursor.getInt(0);
         }
         
         cursor.close();
         
         return highscore;
    }
  
    public void close()
    {
    	if(this.dbHandler != null) {
	    	try {
				this.dbHandler.closeDatabase();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			this.dbHandler = null;
	        Gdx.app.log("FlappyBirdsDatabase", "disposed");
    	}
    }
}