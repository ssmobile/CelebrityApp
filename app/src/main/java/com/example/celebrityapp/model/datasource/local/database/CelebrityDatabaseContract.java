package com.example.celebrityapp.model.datasource.local.database;

public class CelebrityDatabaseContract {

    public static final String DATABASE_NAME = "celebs_db";
    public static final String CELEB_TABLE_NAME = "celebs_table";
    public static final String FILMOGRAPHY_TABLE_NAME = "filmography_table";
    public static final String FILM_TABLE_NAME = "film_table";

    public static final int DATABASE_VERSION = 1;

    public static final String COL_ID = "id";

    //Celebrity table columns
    public static final String COL_CELEB_FNAME = "first_name";
    public static final String COL_CELEB_LNAME = "last_name";
    public static final String COL_CELEB_HEIGHT = "height";
    public static final String COL_CELEB_INDUSTRY = "industry";
    public static final String COL_CELEB_DOB = "dob";
    public static final String COL_CELEB_ISFAV = "is_favorite";



    //Create table queries
    public static final String CREATE_CELEB_TABLE_QUERY =
            String.format("CREATE TABLE %s(%s TEXT, %s TEXT, %s REAL, %s TEXT, %s TEXT," +
                    " %s INTEGER, %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)",
                    CELEB_TABLE_NAME, COL_CELEB_FNAME, COL_CELEB_LNAME, COL_CELEB_HEIGHT, COL_CELEB_INDUSTRY,
                    COL_CELEB_DOB, COL_CELEB_ISFAV, COL_ID);

    public static final String DROP_TABLE =  "DROP TABLE IF EXISTS ";

    public static final String QUERY_SELECT_ALL = "SELECT * FROM ";
}
