package com.example.celebrityapp.model.datasource.local.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.celebrityapp.model.Celebrity;
import com.example.celebrityapp.model.datasource.local.database.CelebrityDatabaseHelper;

import static com.example.celebrityapp.model.datasource.local.contentprovider.CelebrityProviderContract.*;
import static com.example.celebrityapp.model.datasource.local.database.CelebrityDatabaseContract.CELEB_TABLE_NAME;
import static com.example.celebrityapp.model.datasource.local.database.CelebrityDatabaseContract.COL_ID;

public class CelebrityContentProvider extends ContentProvider {
    public static final String TAG = "TAG_Provider";
    private CelebrityDatabaseHelper celebrityDatabaseHelper;
    private UriMatcher uriMatcher;


    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ");
        celebrityDatabaseHelper = new CelebrityDatabaseHelper(getContext());
        uriMatcher = buildUriMatcher();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection , String selection, String[] selectionArgs, String sortby) {
        SQLiteDatabase sqLiteDatabase = celebrityDatabaseHelper.getReadableDatabase();
        Cursor returnCursor = null;

        switch(uriMatcher.match(uri)) {
            case CELEBRITY:
                Log.d(TAG, "query: CELEBRITY");
                returnCursor = sqLiteDatabase.query(
                        CELEB_TABLE_NAME,               //Table queried
                        projection,                     //Columns to return
                        selection,                      //Selection clause
                        selectionArgs,                  //Selection arguments
                        null,                   //group by
                        null,                    //having
                        sortby);                        //sort order
                break;
            case CELEBRITY_ID:
                Log.d(TAG, "query: CELEBRITY_ID");
                returnCursor = sqLiteDatabase.query(
                        CELEB_TABLE_NAME,
                        projection,
                        CelebrityProviderContract.CelebrityEntry._ID + " = ?",
                        new String []{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortby);
                break;
        }

        return returnCursor;
    }

    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)) {
            case CELEBRITY:
                return CelebrityProviderContract.CelebrityEntry.CONTENT_TYPE;
            case CELEBRITY_ID:
                return CelebrityProviderContract.CelebrityEntry.CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        celebrityDatabaseHelper.insertCelebrityIntoDB(Celebrity.fromContentValues(contentValues));
        getContext().getContentResolver().notifyChange(uri, null);


        return uri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase sqLiteDatabase = celebrityDatabaseHelper.getWritableDatabase();
        int id = sqLiteDatabase.delete(CELEB_TABLE_NAME, COL_ID + " = ?", strings);
        return id;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        SQLiteDatabase sqLiteDatabase = celebrityDatabaseHelper.getWritableDatabase();
        int id = sqLiteDatabase.update(CELEB_TABLE_NAME, contentValues, COL_ID + " = ?", strings);
        return id;
    }

    public UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        Log.d(TAG, "buildUriMatcher: " + uriMatcher.toString());
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CELEBRITY, CELEBRITY);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CELEBRITY + "/#" + CELEBRITY_ID, CELEBRITY);
        return uriMatcher;
    }
}
