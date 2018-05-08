package com.example.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Basic database class for the application.
 * The only class that should use this is {@link AppProvider}.
 */
class AppDatabase extends SQLiteOpenHelper {
    private static final String TAG = "AppDatabase";

    public static final String DATABASE_NAME = "LearnEnglish.db";
    public static final int DATABASE_VERSION = 1;

    private static AppDatabase instance = null;

    private AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new AppDatabase(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQuestionsTable());
        db.execSQL(createHintsTable());
        db.execSQL(createUsersTable());
        db.execSQL(createSetsTable());
        db.execSQL(createFlashCardsTable());
        db.execSQL(createLessonsTable());
        db.execSQL(createExercisesTable());
        db.execSQL(createExercisesTypesTable());
        db.execSQL(createCategoriesTable());
        db.execSQL(createSentencesTable());
        db.execSQL(createWordsTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                // upgrade logic from version 1
                break;
            default:
                throw new IllegalStateException("onUpgrade() with unknown newVersion: " + newVersion);
        }
    }

    private String createQuestionsTable() {
        return  "CREATE TABLE " + QuestionsContract.TABLE_NAME + " ("
                + QuestionsContract.Columns._ID                + " INTEGER PRIMARY KEY NOT NULL, "
                + QuestionsContract.Columns.QUESTIONS_QUESTION + " VARCHAR(50) NOT NULL)";
    }

    private String createHintsTable() {
        return  "CREATE TABLE " + HintsContract.TABLE_NAME + " ("
                + HintsContract.Columns._ID               + " INTEGER PRIMARY KEY NOT NULL, "
                + HintsContract.Columns.HINTS_QUESTION_ID + " INTEGER(10) NOT NULL, "
                + HintsContract.Columns.HINTS_USER_ID     + " INTEGER(10) NOT NULL, "
                + HintsContract.Columns.HINTS_ANSWER      + " VARCHAR(20) NOT NULL)";
    }

    private String createUsersTable() {
        return  "CREATE TABLE " + UsersContract.TABLE_NAME + " ("
                + UsersContract.Columns._ID            + " INTEGER PRIMARY KEY NOT NULL, "
                + UsersContract.Columns.USERS_LOGIN    + " VARCHAR(40) UNIQUE NOT NULL, "
                + UsersContract.Columns.USERS_PASSWORD + " VARCHAR(64) NOT NULL, "
                + UsersContract.Columns.USERS_NAME     + " VARCHAR(30) NOT NULL)";
    }

    private String createSetsTable() {
        return  "CREATE TABLE " + SetsContract.TABLE_NAME + " ("
                + SetsContract.Columns._ID          + " INTEGER PRIMARY KEY NOT NULL, "
                + SetsContract.Columns.SETS_USER_ID + " INTEGER(10) NOT NULL, "
                + SetsContract.Columns.SETS_NAME    + " VARCHAR(40) NOT NULL)";
    }

    private String createFlashCardsTable() {
        return  "CREATE TABLE " + FlashcardsContract.TABLE_NAME + " ("
                + FlashcardsContract.Columns._ID                + " INTEGER PRIMARY KEY NOT NULL, "
                + FlashcardsContract.Columns.FLASHCARDS_SET_ID  + " INTEGER(10) NOT NULL, "
                + FlashcardsContract.Columns.FLASHCARDS_WORD_PL + " VARCHAR(50) NOT NULL, "
                + FlashcardsContract.Columns.FLASHCARDS_WORD_EN + " VARCHAR(50) NOT NULL)";
    }

    private String createLessonsTable() {
        return  "CREATE TABLE " + LessonsContract.TABLE_NAME + " ("
                + LessonsContract.Columns._ID                    + " INTEGER PRIMARY KEY NOT NULL, "
                + LessonsContract.Columns.LESSONS_USER_ID        + " INTEGER(10) NOT NULL, "
                + LessonsContract.Columns.LESSONS_EXERCISE_ID    + " INTEGER(10) NOT NULL, "
                + LessonsContract.Columns.LESSONS_CORRECT_ANSWER + " INTEGER(10) NOT NULL, "
                + LessonsContract.Columns.LESSONS_WRONG_ANSWER   + " INTEGER(10) NOT NULL, "
                + LessonsContract.Columns.LESSONS_DATE           + " DATE NOT NULL)";
    }

    private String createExercisesTable() {
        return  "CREATE TABLE " + ExercisesContract.TABLE_NAME + " ("
                + ExercisesContract.Columns._ID                   + " INTEGER PRIMARY KEY NOT NULL, "
                + ExercisesContract.Columns.EXERCISES_CATEGORY_ID + " INTEGER(10) NOT NULL, "
                + ExercisesContract.Columns.EXERCISES_TYPE_ID     + " INTEGER(10) NOT NULL)";
    }

    private String createExercisesTypesTable() {
        return  "CREATE TABLE " + ExercisesTypesContract.TABLE_NAME + " ("
                + ExercisesTypesContract.Columns._ID                 + " INTEGER PRIMARY KEY NOT NULL, "
                + ExercisesTypesContract.Columns.EXERCISESTYPES_NAME + " VARCHAR(50) NOT NULL)";
    }

    private String createCategoriesTable() {
        return  "CREATE TABLE " + CategoriesContract.TABLE_NAME + " ("
                + CategoriesContract.Columns._ID             + " INTEGER PRIMARY KEY NOT NULL, "
                + CategoriesContract.Columns.CATEGORIES_NAME + " VARCHAR(40) NOT NULL)";
    }

    private String createSentencesTable() {
        return  "CREATE TABLE " + SentencesContract.TABLE_NAME + " ("
                + SentencesContract.Columns._ID                   + " INTEGER PRIMARY KEY NOT NULL, "
                + SentencesContract.Columns.SENTENCES_CATEGORY_ID + " INTEGER(10) NOT NULL, "
                + SentencesContract.Columns.SENTENCES_SENTENCE_PL + " VARCHAR(100) NOT NULL, "
                + SentencesContract.Columns.SENTENCES_SENTENCE_EN + " VARCHAR(100) NOT NULL)";
    }

    private String createWordsTable() {
        return  "CREATE TABLE " + WordsContract.TABLE_NAME + " ("
                + WordsContract.Columns._ID               + " INTEGER PRIMARY KEY NOT NULL, "
                + WordsContract.Columns.WORDS_CATEGORY_ID + " INTEGER(10) NOT NULL, "
                + WordsContract.Columns.WORDS_WORD_PL     + " VARCHAR(30) NOT NULL, "
                + WordsContract.Columns.WORDS_WORD_EN     + " VARCHAR(30) NOT NULL, "
                + WordsContract.Columns.WORDS_IMAGE       + " BULB)";
    }
}
