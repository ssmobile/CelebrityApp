package com.example.celebrityapp.model.datasource.local.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.celebrityapp.model.Celebrity;

import java.util.ArrayList;

public class CelebrityDatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = "TAG_DBHelper";


    public CelebrityDatabaseHelper(Context context) {
        super(context, CelebrityDatabaseContract.DATABASE_NAME, null, CelebrityDatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CelebrityDatabaseContract.CREATE_CELEB_TABLE_QUERY);
        Log.d(TAG, CelebrityDatabaseContract.CREATE_CELEB_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CelebrityDatabaseContract.DROP_TABLE +
                        CelebrityDatabaseContract.CELEB_TABLE_NAME);

        sqLiteDatabase.execSQL(CelebrityDatabaseContract.DROP_TABLE +
                        CelebrityDatabaseContract.FILMOGRAPHY_TABLE_NAME);

        sqLiteDatabase.execSQL(CelebrityDatabaseContract.DROP_TABLE +
                        CelebrityDatabaseContract.FILM_TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    //Select All Method
    public ArrayList<Celebrity> getAllCelebrities() {
        ArrayList<Celebrity> returnCelebrityList = new ArrayList<>();//Return list for all found phones in DB
        SQLiteDatabase readableDatabase = this.getReadableDatabase();

        Cursor cursor = readableDatabase.rawQuery(
                CelebrityDatabaseContract.QUERY_SELECT_ALL +
                CelebrityDatabaseContract.CELEB_TABLE_NAME, null);

        if(cursor.moveToFirst()) {
            do {
                Celebrity currentCelebrity = new Celebrity();
                currentCelebrity.setFirstName(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_CELEB_FNAME)));
                currentCelebrity.setLastName(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_CELEB_LNAME)));
                currentCelebrity.setIndustry(cursor.getString(cursor.getColumnIndex(CelebrityDatabaseContract.COL_CELEB_INDUSTRY)));
                currentCelebrity.setDob(String.valueOf(cursor.getInt(cursor.getColumnIndex(CelebrityDatabaseContract.COL_CELEB_DOB))));
                returnCelebrityList.add(currentCelebrity);
            } while(cursor.moveToNext());
        }

        return returnCelebrityList;
    }

    //Insert Celebrity into database
    public void insertCelebrityIntoDB(Celebrity celebrity) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        //Container which is key value pairs, key being the Col of DB, value being
        // what to save in that place
        ContentValues contentValues = celebrity.getContentValues();

        writableDatabase.insert(CelebrityDatabaseContract.CELEB_TABLE_NAME, null, contentValues);
    }

    //Update Celebrity in the database
    public void updateCelebrityInDB(String id, Celebrity passedCelebrityInfo) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = passedCelebrityInfo.getContentValues();

        sqLiteDatabase.update(CelebrityDatabaseContract.CELEB_TABLE_NAME, contentValues, "ID = ?", new String[]{id});
    }

    //Delete a Celebrity
    public void deleteCelebrityInDB(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(CelebrityDatabaseContract.CELEB_TABLE_NAME, "ID = ?", new String[]{id});
    }
}
