package com.example.celebrityapp.model.datasource.local.contentprovider;


import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class CelebrityProviderContract {

   public static final String PATH_CELEBRITY = "celebrity";

   public static final int CELEBRITY = 100;
   public static final int CELEBRITY_ID = 101;
   public static final String CONTENT_AUTHORITY = "com.example.celebrityapp.model.datasource.local.contentprovider";
   public static final String URL = "content://" + CONTENT_AUTHORITY;
   public static final Uri CONTENT_URI = Uri.parse(URL);


    public static final class CelebrityEntry implements BaseColumns {
        public static final Uri CELEBRITY_CONTENT_URI = CONTENT_URI.buildUpon()
                .appendPath(PATH_CELEBRITY).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir" + CELEBRITY_CONTENT_URI + "/celebrity";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item"+ CELEBRITY_CONTENT_URI + "/celebrity";


    }

}
