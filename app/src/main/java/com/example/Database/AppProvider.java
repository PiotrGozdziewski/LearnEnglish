package com.example.Database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Provider for the TaskTimer app.
 * This is the only class that knows about {@link AppDatabase}
 */
public class AppProvider extends ContentProvider {
    private static final String TAG = "AppProvider";

    private AppDatabase appDatabase;

    public static final UriMatcher uriMatcher = buildUriMatcher();

    static final String CONTENT_AUTHORITY = "provider";
    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final int QUESTIONS = 50;
    private static final int QUESTIONS_ID = 51;

    private static final int HINTS = 100;
    private static final int HINTS_ID = 101;

    private static final int USERS = 150;
    private static final int USERS_ID = 151;

    private static final int SETS = 200;
    private static final int SETS_ID = 201;

    private static final int FLASHCARDS = 250;
    private static final int FLASHCARDS_ID = 251;

    private static final int LESSONS = 300;
    private static final int LESSONS_ID = 301;

    private static final int EXERCISES = 350;
    private static final int EXERCISES_ID = 351;

    private static final int EXERCISESTYPES = 400;
    private static final int EXERCISESTYPES_ID = 401;

    private static final int CATEGORIES = 450;
    private static final int CATEGORIES_ID = 451;

    private static final int SENTENCES = 500;
    private static final int SENTENCES_ID = 501;

    private static final int WORDS = 550;
    private static final int WORDS_ID = 551;


    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        //  e.g. content://provider/Questions
        matcher.addURI(CONTENT_AUTHORITY, QuestionsContract.TABLE_NAME, QUESTIONS);
        // e.g. content://provider/Questions/8
        matcher.addURI(CONTENT_AUTHORITY, QuestionsContract.TABLE_NAME + "/#", QUESTIONS_ID);

        matcher.addURI(CONTENT_AUTHORITY, HintsContract.TABLE_NAME, HINTS);
        matcher.addURI(CONTENT_AUTHORITY, HintsContract.TABLE_NAME + "/#", HINTS_ID);

        matcher.addURI(CONTENT_AUTHORITY, UsersContract.TABLE_NAME, USERS);
        matcher.addURI(CONTENT_AUTHORITY, UsersContract.TABLE_NAME + "/#", USERS_ID);

        matcher.addURI(CONTENT_AUTHORITY, SetsContract.TABLE_NAME, SETS);
        matcher.addURI(CONTENT_AUTHORITY, SetsContract.TABLE_NAME + "/#", SETS_ID);

        matcher.addURI(CONTENT_AUTHORITY, FlashcardsContract.TABLE_NAME, FLASHCARDS);
        matcher.addURI(CONTENT_AUTHORITY, FlashcardsContract.TABLE_NAME + "/#", FLASHCARDS_ID);

        matcher.addURI(CONTENT_AUTHORITY, LessonsContract.TABLE_NAME, LESSONS);
        matcher.addURI(CONTENT_AUTHORITY, LessonsContract.TABLE_NAME + "/#", LESSONS_ID);

        matcher.addURI(CONTENT_AUTHORITY, ExercisesContract.TABLE_NAME, EXERCISES);
        matcher.addURI(CONTENT_AUTHORITY, ExercisesContract.TABLE_NAME + "/#", EXERCISES_ID);

        matcher.addURI(CONTENT_AUTHORITY, ExercisesTypesContract.TABLE_NAME, EXERCISESTYPES);
        matcher.addURI(CONTENT_AUTHORITY, ExercisesTypesContract.TABLE_NAME + "/#", EXERCISESTYPES_ID);

        matcher.addURI(CONTENT_AUTHORITY, CategoriesContract.TABLE_NAME, CATEGORIES);
        matcher.addURI(CONTENT_AUTHORITY, CategoriesContract.TABLE_NAME + "/#", CATEGORIES_ID);

        matcher.addURI(CONTENT_AUTHORITY, SentencesContract.TABLE_NAME, SENTENCES);
        matcher.addURI(CONTENT_AUTHORITY, SentencesContract.TABLE_NAME + "/#", SENTENCES_ID);

        matcher.addURI(CONTENT_AUTHORITY, WordsContract.TABLE_NAME, WORDS);
        matcher.addURI(CONTENT_AUTHORITY, WordsContract.TABLE_NAME + "/#", WORDS_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        appDatabase = AppDatabase.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query: called with URI " + uri);
        final int match = uriMatcher.match(uri);
        Log.d(TAG, "query: match is " + match);

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (match) {
            case QUESTIONS:
                queryBuilder.setTables(QuestionsContract.TABLE_NAME);
                break;

            case QUESTIONS_ID:
                queryBuilder.setTables(QuestionsContract.TABLE_NAME);
                long questionId = QuestionsContract.getQuestionId(uri);
                queryBuilder.appendWhere(QuestionsContract.Columns._ID + " = " + questionId);
                break;

            case HINTS:
                queryBuilder.setTables(HintsContract.TABLE_NAME);
                break;

            case HINTS_ID:
                queryBuilder.setTables(HintsContract.TABLE_NAME);
                long hintId = HintsContract.getHintId(uri);
                queryBuilder.appendWhere(HintsContract.Columns._ID + " = " + hintId);
                break;

            case USERS:
                queryBuilder.setTables(UsersContract.TABLE_NAME);
                break;

            case USERS_ID:
                queryBuilder.setTables(UsersContract.TABLE_NAME);
                long userId = UsersContract.getUserId(uri);
                queryBuilder.appendWhere(UsersContract.Columns._ID + " = " + userId);
                break;

            case SETS:
                queryBuilder.setTables(SetsContract.TABLE_NAME);
                break;

            case SETS_ID:
                queryBuilder.setTables(SetsContract.TABLE_NAME);
                long setId = SetsContract.getSetId(uri);
                queryBuilder.appendWhere(SetsContract.Columns._ID + " = " + setId);
                break;

            case FLASHCARDS:
                queryBuilder.setTables(FlashcardsContract.TABLE_NAME);
                break;

            case FLASHCARDS_ID:
                queryBuilder.setTables(FlashcardsContract.TABLE_NAME);
                long flashcardId = FlashcardsContract.getFlashcardId(uri);
                queryBuilder.appendWhere(FlashcardsContract.Columns._ID + " = " + flashcardId);
                break;

            case LESSONS:
                queryBuilder.setTables(LessonsContract.TABLE_NAME);
                break;

            case LESSONS_ID:
                queryBuilder.setTables(LessonsContract.TABLE_NAME);
                long lessonId = LessonsContract.getLessonId(uri);
                queryBuilder.appendWhere(LessonsContract.Columns._ID + " = " + lessonId);
                break;

            case EXERCISES:
                queryBuilder.setTables(ExercisesContract.TABLE_NAME);
                break;

            case EXERCISES_ID:
                queryBuilder.setTables(ExercisesContract.TABLE_NAME);
                long exerciseId = ExercisesContract.getExerciseId(uri);
                queryBuilder.appendWhere(ExercisesContract.Columns._ID + " = " + exerciseId);
                break;

            case EXERCISESTYPES:
                queryBuilder.setTables(ExercisesTypesContract.TABLE_NAME);
                break;

            case EXERCISESTYPES_ID:
                queryBuilder.setTables(ExercisesTypesContract.TABLE_NAME);
                long exerciseTypeId = ExercisesTypesContract.getExerciseTypeId(uri);
                queryBuilder.appendWhere(ExercisesTypesContract.Columns._ID + " = " + exerciseTypeId);
                break;

            case CATEGORIES:
                queryBuilder.setTables(CategoriesContract.TABLE_NAME);
                break;

            case CATEGORIES_ID:
                queryBuilder.setTables(CategoriesContract.TABLE_NAME);
                long categoryId = CategoriesContract.getCategoryId(uri);
                queryBuilder.appendWhere(CategoriesContract.Columns._ID + " = " + categoryId);
                break;

            case SENTENCES:
                queryBuilder.setTables(SentencesContract.TABLE_NAME);
                break;

            case SENTENCES_ID:
                queryBuilder.setTables(SentencesContract.TABLE_NAME);
                long sentenceId = SentencesContract.getSentenceId(uri);
                queryBuilder.appendWhere(SentencesContract.Columns._ID + " = " + sentenceId);
                break;

            case WORDS:
                queryBuilder.setTables(WordsContract.TABLE_NAME);
                break;

            case WORDS_ID:
                queryBuilder.setTables(WordsContract.TABLE_NAME);
                long wordId = WordsContract.getWordId(uri);
                queryBuilder.appendWhere(WordsContract.Columns._ID + " = " + wordId);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = appDatabase.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case QUESTIONS:
                return QuestionsContract.CONTENT_TYPE;

            case QUESTIONS_ID:
                return QuestionsContract.CONTENT_ITEM_TYPE;

            case HINTS:
                return HintsContract.CONTENT_TYPE;

            case HINTS_ID:
                return HintsContract.CONTENT_ITEM_TYPE;

            case USERS:
                return UsersContract.CONTENT_TYPE;

            case USERS_ID:
                return UsersContract.CONTENT_ITEM_TYPE;

            case SETS:
                return SetsContract.CONTENT_TYPE;

            case SETS_ID:
                return SetsContract.CONTENT_ITEM_TYPE;

            case FLASHCARDS:
                return FlashcardsContract.CONTENT_TYPE;

            case FLASHCARDS_ID:
                return FlashcardsContract.CONTENT_ITEM_TYPE;

            case LESSONS:
                return LessonsContract.CONTENT_TYPE;

            case LESSONS_ID:
                return LessonsContract.CONTENT_ITEM_TYPE;

            case EXERCISES:
                return ExercisesContract.CONTENT_TYPE;

            case EXERCISES_ID:
                return ExercisesContract.CONTENT_ITEM_TYPE;

            case EXERCISESTYPES:
                return ExercisesTypesContract.CONTENT_TYPE;

            case EXERCISESTYPES_ID:
                return ExercisesTypesContract.CONTENT_ITEM_TYPE;

            case CATEGORIES:
                return CategoriesContract.CONTENT_TYPE;

            case CATEGORIES_ID:
                return CategoriesContract.CONTENT_ITEM_TYPE;

            case SENTENCES:
                return SentencesContract.CONTENT_TYPE;

            case SENTENCES_ID:
                return SentencesContract.CONTENT_ITEM_TYPE;

            case WORDS:
                return WordsContract.CONTENT_TYPE;

            case WORDS_ID:
                return WordsContract.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("unknown Uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "Entering insert, called with uri:" + uri);
        final int match = uriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        // nie wywolujemy tutaj appDatabase.getWritableDatabase(); bo jest to dosc wolna operacja
        // i jezeli match nie bedzie nigdzie pasowal to niepotrzebnie obciazymy telefon
        final SQLiteDatabase db;

        Uri returnUri;
        long recordId;

        switch (match) {
            case QUESTIONS:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(QuestionsContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = QuestionsContract.buildQuestionUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case HINTS:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(HintsContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = HintsContract.buildHintUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case USERS:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(UsersContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = UsersContract.buildUserUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case SETS:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(SetsContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = SetsContract.buildSetUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case FLASHCARDS:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(FlashcardsContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = FlashcardsContract.buildFlashcardUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case LESSONS:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(LessonsContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = LessonsContract.buildLessonUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case EXERCISES:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(ExercisesContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = ExercisesContract.buildExerciseUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case EXERCISESTYPES:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(ExercisesTypesContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = ExercisesTypesContract.buildExerciseTypeUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case CATEGORIES:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(CategoriesContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = CategoriesContract.buildCategoryUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case SENTENCES:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(SentencesContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = SentencesContract.buildSentenceUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case WORDS:
                db = appDatabase.getWritableDatabase();
                recordId = db.insert(WordsContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = WordsContract.buildWordUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        Log.d(TAG, "Exiting insert, returning " + returnUri);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update called with uri " + uri);
        final int match = uriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;
        int count;

        String selectionCriteria;

        switch (match) {
            case QUESTIONS:
                db = appDatabase.getWritableDatabase();
                count = db.delete(QuestionsContract.TABLE_NAME, selection, selectionArgs);
                break;

            case QUESTIONS_ID:
                db = appDatabase.getWritableDatabase();
                long questionId = QuestionsContract.getQuestionId(uri);
                selectionCriteria = QuestionsContract.Columns._ID + " = " + questionId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(QuestionsContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case HINTS:
                db = appDatabase.getWritableDatabase();
                count = db.delete(HintsContract.TABLE_NAME, selection, selectionArgs);
                break;

            case HINTS_ID:
                db = appDatabase.getWritableDatabase();
                long hintId = HintsContract.getHintId(uri);
                selectionCriteria = HintsContract.Columns._ID + " = " + hintId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(HintsContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case USERS:
                db = appDatabase.getWritableDatabase();
                count = db.delete(UsersContract.TABLE_NAME, selection, selectionArgs);
                break;

            case USERS_ID:
                db = appDatabase.getWritableDatabase();
                long userId = UsersContract.getUserId(uri);
                selectionCriteria = UsersContract.Columns._ID + " = " + userId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(UsersContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case SETS:
                db = appDatabase.getWritableDatabase();
                count = db.delete(SetsContract.TABLE_NAME, selection, selectionArgs);
                break;

            case SETS_ID:
                db = appDatabase.getWritableDatabase();
                long setId = SetsContract.getSetId(uri);
                selectionCriteria = SetsContract.Columns._ID + " = " + setId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(SetsContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case FLASHCARDS:
                db = appDatabase.getWritableDatabase();
                count = db.delete(FlashcardsContract.TABLE_NAME, selection, selectionArgs);
                break;

            case FLASHCARDS_ID:
                db = appDatabase.getWritableDatabase();
                long flashcardId = FlashcardsContract.getFlashcardId(uri);
                selectionCriteria = FlashcardsContract.Columns._ID + " = " + flashcardId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(FlashcardsContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case LESSONS:
                db = appDatabase.getWritableDatabase();
                count = db.delete(LessonsContract.TABLE_NAME, selection, selectionArgs);
                break;

            case LESSONS_ID:
                db = appDatabase.getWritableDatabase();
                long lessonId = LessonsContract.getLessonId(uri);
                selectionCriteria = LessonsContract.Columns._ID + " = " + lessonId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(LessonsContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case EXERCISES:
                db = appDatabase.getWritableDatabase();
                count = db.delete(ExercisesContract.TABLE_NAME, selection, selectionArgs);
                break;

            case EXERCISES_ID:
                db = appDatabase.getWritableDatabase();
                long exerciseId = ExercisesContract.getExerciseId(uri);
                selectionCriteria = ExercisesContract.Columns._ID + " = " + exerciseId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(ExercisesContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case EXERCISESTYPES:
                db = appDatabase.getWritableDatabase();
                count = db.delete(ExercisesTypesContract.TABLE_NAME, selection, selectionArgs);
                break;

            case EXERCISESTYPES_ID:
                db = appDatabase.getWritableDatabase();
                long exerciseTypeId = ExercisesTypesContract.getExerciseTypeId(uri);
                selectionCriteria = ExercisesTypesContract.Columns._ID + " = " + exerciseTypeId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(ExercisesTypesContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case CATEGORIES:
                db = appDatabase.getWritableDatabase();
                count = db.delete(CategoriesContract.TABLE_NAME, selection, selectionArgs);
                break;

            case CATEGORIES_ID:
                db = appDatabase.getWritableDatabase();
                long categoryId = CategoriesContract.getCategoryId(uri);
                selectionCriteria = CategoriesContract.Columns._ID + " = " + categoryId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(CategoriesContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case SENTENCES:
                db = appDatabase.getWritableDatabase();
                count = db.delete(SentencesContract.TABLE_NAME, selection, selectionArgs);
                break;

            case SENTENCES_ID:
                db = appDatabase.getWritableDatabase();
                long sentenceId = SentencesContract.getSentenceId(uri);
                selectionCriteria = SentencesContract.Columns._ID + " = " + sentenceId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(SentencesContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case WORDS:
                db = appDatabase.getWritableDatabase();
                count = db.delete(WordsContract.TABLE_NAME, selection, selectionArgs);
                break;

            case WORDS_ID:
                db = appDatabase.getWritableDatabase();
                long wordId = WordsContract.getWordId(uri);
                selectionCriteria = WordsContract.Columns._ID + " = " + wordId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(WordsContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        Log.d(TAG, "Exiting update, returning " + count);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update called with uri " + uri);
        final int match = uriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;
        int count;

        String selectionCriteria;

        switch (match) {
            case QUESTIONS:
                db = appDatabase.getWritableDatabase();
                count = db.update(QuestionsContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case QUESTIONS_ID:
                db = appDatabase.getWritableDatabase();
                long questionId = QuestionsContract.getQuestionId(uri);
                selectionCriteria = QuestionsContract.Columns._ID + " = " + questionId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(QuestionsContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case HINTS:
                db = appDatabase.getWritableDatabase();
                count = db.update(HintsContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case HINTS_ID:
                db = appDatabase.getWritableDatabase();
                long hintId = HintsContract.getHintId(uri);
                selectionCriteria = HintsContract.Columns._ID + " = " + hintId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(HintsContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case USERS:
                db = appDatabase.getWritableDatabase();
                count = db.update(UsersContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case USERS_ID:
                db = appDatabase.getWritableDatabase();
                long userId = UsersContract.getUserId(uri);
                selectionCriteria = UsersContract.Columns._ID + " = " + userId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(UsersContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case SETS:
                db = appDatabase.getWritableDatabase();
                count = db.update(SetsContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case SETS_ID:
                db = appDatabase.getWritableDatabase();
                long setId = SetsContract.getSetId(uri);
                selectionCriteria = SetsContract.Columns._ID + " = " + setId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(SetsContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case FLASHCARDS:
                db = appDatabase.getWritableDatabase();
                count = db.update(FlashcardsContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case FLASHCARDS_ID:
                db = appDatabase.getWritableDatabase();
                long flashcardId = FlashcardsContract.getFlashcardId(uri);
                selectionCriteria = FlashcardsContract.Columns._ID + " = " + flashcardId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(FlashcardsContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case LESSONS:
                db = appDatabase.getWritableDatabase();
                count = db.update(LessonsContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case LESSONS_ID:
                db = appDatabase.getWritableDatabase();
                long lessonId = LessonsContract.getLessonId(uri);
                selectionCriteria = LessonsContract.Columns._ID + " = " + lessonId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(LessonsContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case EXERCISES:
                db = appDatabase.getWritableDatabase();
                count = db.update(ExercisesContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case EXERCISES_ID:
                db = appDatabase.getWritableDatabase();
                long exerciseId = ExercisesContract.getExerciseId(uri);
                selectionCriteria = ExercisesContract.Columns._ID + " = " + exerciseId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(ExercisesContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case EXERCISESTYPES:
                db = appDatabase.getWritableDatabase();
                count = db.update(ExercisesTypesContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case EXERCISESTYPES_ID:
                db = appDatabase.getWritableDatabase();
                long exerciseTypeId = ExercisesTypesContract.getExerciseTypeId(uri);
                selectionCriteria = ExercisesTypesContract.Columns._ID + " = " + exerciseTypeId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(ExercisesTypesContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case CATEGORIES:
                db = appDatabase.getWritableDatabase();
                count = db.update(CategoriesContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case CATEGORIES_ID:
                db = appDatabase.getWritableDatabase();
                long categoryId = CategoriesContract.getCategoryId(uri);
                selectionCriteria = CategoriesContract.Columns._ID + " = " + categoryId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(CategoriesContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case SENTENCES:
                db = appDatabase.getWritableDatabase();
                count = db.update(SentencesContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case SENTENCES_ID:
                db = appDatabase.getWritableDatabase();
                long sentenceId = SentencesContract.getSentenceId(uri);
                selectionCriteria = SentencesContract.Columns._ID + " = " + sentenceId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(SentencesContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case WORDS:
                db = appDatabase.getWritableDatabase();
                count = db.update(WordsContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case WORDS_ID:
                db = appDatabase.getWritableDatabase();
                long wordId = WordsContract.getWordId(uri);
                selectionCriteria = WordsContract.Columns._ID + " = " + wordId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(WordsContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        Log.d(TAG, "Exiting update, returning " + count);
        return count;
    }
}