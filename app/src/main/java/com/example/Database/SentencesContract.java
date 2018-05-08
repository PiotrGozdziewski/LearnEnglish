package com.example.Database;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.example.Database.AppProvider.CONTENT_AUTHORITY;
import static com.example.Database.AppProvider.CONTENT_AUTHORITY_URI;

public class SentencesContract {
    static final String TABLE_NAME = "Sentences";

    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String SENTENCES_CATEGORY_ID = "Category_id";
        public static final String SENTENCES_SENTENCE_PL = "Sentence_pl";
        public static final String SENTENCES_SENTENCE_EN = "Sentence_en";

        private Columns() {

        }
    }

    /**
     * The URI to access the Sentences tables
     */
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    public static Uri buildSentenceUri(long taskId) {
        return ContentUris.withAppendedId(CONTENT_URI, taskId);
    }

    public static long getSentenceId(Uri uri) {
        return ContentUris.parseId(uri);
    }
}
