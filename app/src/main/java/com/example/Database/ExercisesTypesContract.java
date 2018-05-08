package com.example.Database;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.example.Database.AppProvider.CONTENT_AUTHORITY;
import static com.example.Database.AppProvider.CONTENT_AUTHORITY_URI;

public class ExercisesTypesContract {
    static final String TABLE_NAME = "ExercisesTypes";

    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String EXERCISESTYPES_NAME = "Name";

        private Columns() {

        }
    }

    /**
     * The URI to access the ExercisesTypes tables
     */
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    public static Uri buildExerciseTypeUri(long taskId) {
        return ContentUris.withAppendedId(CONTENT_URI, taskId);
    }

    public static long getExerciseTypeId(Uri uri) {
        return ContentUris.parseId(uri);
    }
}
