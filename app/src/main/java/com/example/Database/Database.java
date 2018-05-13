package com.example.Database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.util.Log;

import com.example.Uzytkownik;
import com.example.aplikacja_screen.MainActivity;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class Database {

    private static final String TAG = "Database";

    private ContentResolver contentResolver;

    public Database(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    // Wszystkie nowe funkcje dajemy pod tym komentarzem

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public Uzytkownik loginUser(String login, String pass) {
        Uzytkownik uzytkownik = null;

        String[] projection = {
                UsersContract.Columns._ID,
                UsersContract.Columns.USERS_LOGIN,
                UsersContract.Columns.USERS_PASSWORD,
                UsersContract.Columns.USERS_NAME};

        String selection = UsersContract.Columns.USERS_LOGIN + " = ?";
        String[] args = {login};

        Cursor cursor = contentResolver.query(UsersContract.CONTENT_URI,
                projection,
                selection,
                args,
                UsersContract.Columns._ID);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                if (cursor.getString(2).equals(pass)) {
                    uzytkownik = new Uzytkownik(cursor.getInt(0), cursor.getString(1), cursor.getString(3));
                }
            }
            cursor.close();
        }

        return uzytkownik;
    }

    public Cursor getQuestions() {
        String[] projection = {
                QuestionsContract.Columns._ID,
                QuestionsContract.Columns.QUESTIONS_QUESTION};

        Cursor cursor = contentResolver.query(QuestionsContract.CONTENT_URI,
                projection,
                null,
                null,
                QuestionsContract.Columns._ID);
        return cursor;
    }

    //funkcja dla wyświetlenia zestawów
    public Cursor getSets() {
        String[] projection = {
                SetsContract.Columns._ID,
                SetsContract.Columns.SETS_USER_ID,
                SetsContract.Columns.SETS_NAME};

        Cursor cursor = contentResolver.query(SetsContract.CONTENT_URI,
                projection,
                null,
                null,
                SetsContract.Columns._ID);
        return cursor;
    }

    //usuwanie zestawu o podanym ID
    public void deleteFromSets(int setId) {
        String selection = SetsContract.Columns._ID + " = ?";
        String[] args = {String.valueOf(setId)};
        int count = contentResolver.delete(SetsContract.CONTENT_URI, selection, args);
    }

    //funkcja dla wyświetlania fiszek
    public Cursor getFlashcards() {
        String[] projection = {
                FlashcardsContract.Columns._ID,
                FlashcardsContract.Columns.FLASHCARDS_SET_ID,
                FlashcardsContract.Columns.FLASHCARDS_WORD_PL,
                FlashcardsContract.Columns.FLASHCARDS_WORD_EN};

        Cursor cursor = contentResolver.query(FlashcardsContract.CONTENT_URI,
                projection,
                null,
                null,
                FlashcardsContract.Columns._ID);
        return cursor;
    }

    //usuwanie fiszki o podanym ID
    public void deleteFromFlashcards(int flashcardID) {
        String selection = FlashcardsContract.Columns._ID + " =?";
        String[] args = {String.valueOf(flashcardID)};
        int count = contentResolver.delete(FlashcardsContract.CONTENT_URI, selection, args);
    }

    //usuwanie wszystkich fiszek
    public void deleteFlashcards(int setID){
        String selection = FlashcardsContract.Columns.FLASHCARDS_SET_ID + " =?";
        String[] args = {String.valueOf((setID))};
        int count = contentResolver.delete(FlashcardsContract.CONTENT_URI, selection, args);
    }

    //edytowanie fiszek
    public void updateFlashcards(int flashCardsID, int flashCardsSetID, String wordPL, String wordEN) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FlashcardsContract.Columns.FLASHCARDS_SET_ID, flashCardsSetID);
        contentValues.put(FlashcardsContract.Columns.FLASHCARDS_WORD_PL, wordPL);
        contentValues.put(FlashcardsContract.Columns.FLASHCARDS_WORD_EN, wordEN);
        String selection = FlashcardsContract.Columns._ID + " =?";
        String[] args = {String.valueOf(flashCardsID)};
        int count = contentResolver.update(FlashcardsContract.CONTENT_URI, contentValues, selection, args);
    }

    //edytowanie loginu użytkownika
    public int updateUserLogin(int usersID, String login) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersContract.Columns.USERS_LOGIN, login);
        String selection = UsersContract.Columns._ID + " =?";
        String[] args = {String.valueOf(usersID)};
        int count = contentResolver.update(UsersContract.CONTENT_URI, contentValues, selection, args);
        return count;
    }


    //edytowanie hasła użytkownika
    public int updateUserPassword(int usersID, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersContract.Columns.USERS_PASSWORD, password);
        String selection = UsersContract.Columns._ID + " =?";
        String[] args = {String.valueOf(usersID)};
        int count = contentResolver.update(UsersContract.CONTENT_URI, contentValues, selection, args);
        return count;
    }

    //pobranie informaji o uzytkowniku o podanym ID
    public Cursor getUser(int userID) {
        String[] projection = {
                UsersContract.Columns._ID,
                UsersContract.Columns.USERS_LOGIN,
                UsersContract.Columns.USERS_PASSWORD,
                UsersContract.Columns.USERS_NAME};
        String selection = UsersContract.Columns._ID + " =?";
        String[] args = {String.valueOf(userID)};
        Cursor cursor = contentResolver.query(UsersContract.CONTENT_URI,
                projection,
                selection,
                args,
                UsersContract.Columns._ID);
        return cursor;
    }

    //pobranie info o uzytkownikach
    public Cursor getUsers() {
        String[] projection = {
                UsersContract.Columns._ID,
                UsersContract.Columns.USERS_LOGIN,
                UsersContract.Columns.USERS_PASSWORD,
                UsersContract.Columns.USERS_NAME};
        Cursor cursor = contentResolver.query(UsersContract.CONTENT_URI,
                projection,
                null,
                null,
                UsersContract.Columns._ID);
        return cursor;
    }

    //pobranie inf o podpowiedziach
    public Cursor getHints() {
        String[] projection = {
                HintsContract.Columns._ID,
                HintsContract.Columns.HINTS_QUESTION_ID,
                HintsContract.Columns.HINTS_USER_ID,
                HintsContract.Columns.HINTS_ANSWER};
        Cursor cursor = contentResolver.query(HintsContract.CONTENT_URI,
                projection,
                null,
                null,
                HintsContract.Columns._ID);
        return cursor;
    }

    //pobranie inf o pytaniach
    public Cursor getQuestion() {
        String[] projection = {
                QuestionsContract.Columns._ID,
                QuestionsContract.Columns.QUESTIONS_QUESTION};
        Cursor cursor = contentResolver.query(QuestionsContract.CONTENT_URI,
                projection,
                null,
                null,
                QuestionsContract.Columns._ID);
        return cursor;
    }


    // Wszystkie nowe funkcje dajemy nad tym komentarzem


    public Uri insertIntoQuestions(String question) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsContract.Columns.QUESTIONS_QUESTION, question);
        Uri uri = contentResolver.insert(QuestionsContract.CONTENT_URI, contentValues);
        return uri;
    }

    public int updateQuestions(int questionId, String updatedQuestion) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsContract.Columns.QUESTIONS_QUESTION, updatedQuestion);
        String selection = QuestionsContract.Columns._ID + " = ?";
        String[] args = {String.valueOf(questionId)};
        int count = contentResolver.update(QuestionsContract.CONTENT_URI, contentValues, selection, args);
        Log.d(TAG, "onCreate: " + count + " record(s) updated");
        return count;
    }

    public int deleteFromQuestions(int questionId) {
        String selection = QuestionsContract.Columns._ID + " = ?";
        String[] args = {String.valueOf(questionId)};
        int count = contentResolver.delete(QuestionsContract.CONTENT_URI, selection, args);
        Log.d(TAG, "onCreate: " + count + " record(s) deleted");
        return count;
    }

    public void queryQuestions() {
        String[] projection = {
                QuestionsContract.Columns._ID,
                QuestionsContract.Columns.QUESTIONS_QUESTION};

        Cursor cursor = contentResolver.query(QuestionsContract.CONTENT_URI,
                projection,
                null,
                null,
                QuestionsContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    public String getQuestionById(int questionId) {
        String question = null;

        String[] projection = {QuestionsContract.Columns.QUESTIONS_QUESTION};

        String selection = QuestionsContract.Columns._ID + " = ?";
        String[] args = {Integer.toString(questionId)};

        Cursor cursor = contentResolver.query(QuestionsContract.CONTENT_URI,
                projection,
                selection,
                args,
                QuestionsContract.Columns._ID);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                return cursor.getString(0);
            }
            cursor.close();
        }
        return question;
    }

    public Uri insertIntoHints(int questionId, int userId, String answer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HintsContract.Columns.HINTS_QUESTION_ID, questionId);
        contentValues.put(HintsContract.Columns.HINTS_USER_ID, userId);
        contentValues.put(HintsContract.Columns.HINTS_ANSWER, answer);
        Uri uri = contentResolver.insert(HintsContract.CONTENT_URI, contentValues);
        return uri;
    }

    public void queryHints() {
        String[] projection = {
                HintsContract.Columns._ID,
                HintsContract.Columns.HINTS_QUESTION_ID,
                HintsContract.Columns.HINTS_USER_ID,
                HintsContract.Columns.HINTS_ANSWER};

        Cursor cursor = contentResolver.query(HintsContract.CONTENT_URI,
                projection,
                null,
                null,
                HintsContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    public Uri insertIntoUsers(String login, String password, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersContract.Columns.USERS_LOGIN, login);
        contentValues.put(UsersContract.Columns.USERS_PASSWORD, password);
        contentValues.put(UsersContract.Columns.USERS_NAME, name);
        Uri uri = contentResolver.insert(UsersContract.CONTENT_URI, contentValues);
        return uri;
    }

    public void queryUsers() {
        String[] projection = {
                UsersContract.Columns._ID,
                UsersContract.Columns.USERS_LOGIN,
                UsersContract.Columns.USERS_PASSWORD,
                UsersContract.Columns.USERS_NAME};

        Cursor cursor = contentResolver.query(UsersContract.CONTENT_URI,
                projection,
                null,
                null,
                HintsContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    public Uri insertIntoSets(int userId, String setName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SetsContract.Columns.SETS_USER_ID, userId);
        contentValues.put(SetsContract.Columns.SETS_NAME, setName);
        Uri uri = contentResolver.insert(SetsContract.CONTENT_URI, contentValues);
        return uri;
    }

    public void querySets() {
        String[] projection = {
                SetsContract.Columns._ID,
                SetsContract.Columns.SETS_USER_ID,
                SetsContract.Columns.SETS_NAME};

        Cursor cursor = contentResolver.query(SetsContract.CONTENT_URI,
                projection,
                null,
                null,
                SetsContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    public Uri insertIntoFlashCards(int setId, String wordPl, String wordEn) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FlashcardsContract.Columns.FLASHCARDS_SET_ID, setId);
        contentValues.put(FlashcardsContract.Columns.FLASHCARDS_WORD_PL, wordPl);
        contentValues.put(FlashcardsContract.Columns.FLASHCARDS_WORD_EN, wordEn);
        Uri uri = contentResolver.insert(FlashcardsContract.CONTENT_URI, contentValues);
        return uri;
    }

    public void queryFlashCards() {
        String[] projection = {
                FlashcardsContract.Columns._ID,
                FlashcardsContract.Columns.FLASHCARDS_SET_ID,
                FlashcardsContract.Columns.FLASHCARDS_WORD_PL,
                FlashcardsContract.Columns.FLASHCARDS_WORD_EN};

        Cursor cursor = contentResolver.query(FlashcardsContract.CONTENT_URI,
                projection,
                null,
                null,
                FlashcardsContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    /**
     * Format date = "2007-01-01 10:00:00".
     */
    public Uri insertIntoLessons(int userId, int exerciseId, String correctAnswer, String wrongAnswer, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LessonsContract.Columns.LESSONS_USER_ID, userId);
        contentValues.put(LessonsContract.Columns.LESSONS_EXERCISE_ID, exerciseId);
        contentValues.put(LessonsContract.Columns.LESSONS_CORRECT_ANSWER, correctAnswer);
        contentValues.put(LessonsContract.Columns.LESSONS_WRONG_ANSWER, wrongAnswer);
        contentValues.put(LessonsContract.Columns.LESSONS_DATE, date);
        Uri uri = contentResolver.insert(LessonsContract.CONTENT_URI, contentValues);
        return uri;
    }

    public void queryLessons() {
        String[] projection = {
                LessonsContract.Columns._ID,
                LessonsContract.Columns.LESSONS_USER_ID,
                LessonsContract.Columns.LESSONS_EXERCISE_ID,
                LessonsContract.Columns.LESSONS_CORRECT_ANSWER,
                LessonsContract.Columns.LESSONS_WRONG_ANSWER,
                LessonsContract.Columns.LESSONS_DATE};

        Cursor cursor = contentResolver.query(LessonsContract.CONTENT_URI,
                projection,
                null,
                null,
                LessonsContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    public Uri insertIntoExercises(int categoryId, int typeId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExercisesContract.Columns.EXERCISES_CATEGORY_ID, categoryId);
        contentValues.put(ExercisesContract.Columns.EXERCISES_TYPE_ID, typeId);
        Uri uri = contentResolver.insert(ExercisesContract.CONTENT_URI, contentValues);
        return uri;
    }

    public void queryExercises() {
        String[] projection = {
                ExercisesContract.Columns._ID,
                ExercisesContract.Columns.EXERCISES_CATEGORY_ID,
                ExercisesContract.Columns.EXERCISES_TYPE_ID};

        Cursor cursor = contentResolver.query(ExercisesContract.CONTENT_URI,
                projection,
                null,
                null,
                ExercisesContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    public int getExerciseTypeId(String typeName) {
        String[] projection = {ExercisesTypesContract.Columns._ID};

        String selection = ExercisesTypesContract.Columns.EXERCISESTYPES_NAME + " = ?";
        String[] args = {typeName};

        Cursor cursor = contentResolver.query(ExercisesTypesContract.CONTENT_URI,
                projection,
                selection,
                args,
                ExercisesTypesContract.Columns._ID);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            }
            cursor.close();
        }

        return -1;
    }

    public Uri insertIntoExercisesTypes(String exerciseTypeName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExercisesTypesContract.Columns.EXERCISESTYPES_NAME, exerciseTypeName);
        Uri uri = contentResolver.insert(ExercisesTypesContract.CONTENT_URI, contentValues);
        return uri;
    }

    public void queryExercisesTypes() {
        String[] projection = {
                ExercisesTypesContract.Columns._ID,
                ExercisesTypesContract.Columns.EXERCISESTYPES_NAME};

        Cursor cursor = contentResolver.query(ExercisesTypesContract.CONTENT_URI,
                projection,
                null,
                null,
                ExercisesTypesContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    public Uri insertIntoCategories(String categoryName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriesContract.Columns.CATEGORIES_NAME, categoryName);
        Uri uri = contentResolver.insert(CategoriesContract.CONTENT_URI, contentValues);
        return uri;
    }

    public void queryCategories() {
        String[] projection = {
                CategoriesContract.Columns._ID,
                CategoriesContract.Columns.CATEGORIES_NAME};

        Cursor cursor = contentResolver.query(CategoriesContract.CONTENT_URI,
                projection,
                null,
                null,
                CategoriesContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    public int getCategoryId(String categoryName) {
        String[] projection = {CategoriesContract.Columns._ID};

        String selection = CategoriesContract.Columns.CATEGORIES_NAME + " = ?";
        String[] args = {categoryName};

        Cursor cursor = contentResolver.query(CategoriesContract.CONTENT_URI,
                projection,
                selection,
                args,
                CategoriesContract.Columns._ID);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            }
            cursor.close();
        }

        return -1;
    }

    public Uri insertIntoSentences(int categoryId, String sentencePl, String sentenceEn) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SentencesContract.Columns.SENTENCES_CATEGORY_ID, categoryId);
        contentValues.put(SentencesContract.Columns.SENTENCES_SENTENCE_PL, sentencePl);
        contentValues.put(SentencesContract.Columns.SENTENCES_SENTENCE_EN, sentenceEn);
        Uri uri = contentResolver.insert(SentencesContract.CONTENT_URI, contentValues);
        return uri;
    }

    public void querySentences() {
        String[] projection = {
                SentencesContract.Columns._ID,
                SentencesContract.Columns.SENTENCES_CATEGORY_ID,
                SentencesContract.Columns.SENTENCES_SENTENCE_PL,
                SentencesContract.Columns.SENTENCES_SENTENCE_EN};

        Cursor cursor = contentResolver.query(SentencesContract.CONTENT_URI,
                projection,
                null,
                null,
                SentencesContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    public Uri insertIntoWords(int categoryId, String wordPl, String wordEn, byte[] image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WordsContract.Columns.WORDS_CATEGORY_ID, categoryId);
        contentValues.put(WordsContract.Columns.WORDS_WORD_PL, wordPl);
        contentValues.put(WordsContract.Columns.WORDS_WORD_EN, wordEn);
        contentValues.put(WordsContract.Columns.WORDS_IMAGE, image);
        Uri uri = contentResolver.insert(WordsContract.CONTENT_URI, contentValues);
        return uri;
    }

    public void queryWords() {
        String[] projection = {
                WordsContract.Columns._ID,
                WordsContract.Columns.WORDS_CATEGORY_ID,
                WordsContract.Columns.WORDS_WORD_PL,
                WordsContract.Columns.WORDS_WORD_EN};

        Cursor cursor = contentResolver.query(WordsContract.CONTENT_URI,
                projection,
                null,
                null,
                WordsContract.Columns._ID);

        if (cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ===========================");
            }
            cursor.close();
        }
    }

    public void loadFromExcelFile(String fileName) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(MainActivity.PATH + fileName));
            HSSFWorkbook wb = new HSSFWorkbook(fs);

            int numOfSheets = wb.getNumberOfSheets();

            for (int i = 0; i < numOfSheets; i++) {
                HSSFSheet sheet = wb.getSheetAt(i);
                String sheetName = sheet.getSheetName();

                HSSFRow row;
                HSSFCell cell;

                String[] sheetNameSplit = sheetName.split("-");
                String tableName = sheetNameSplit[0];

                String category = "";
                int categoryId;

                switch (tableName) {
                    case "Categories":
                        for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                            row = sheet.getRow(j);
                            if (row != null) {
                                for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
                                    cell = row.getCell((short) c);
                                    if (cell != null) {
                                        String categoryName = cell.getStringCellValue();
                                        insertIntoCategories(categoryName);
                                    }
                                }
                            }
                        }
                        break;

                    case "Words":
                        category = sheetNameSplit[1];
                        categoryId = getCategoryId(category);

                        for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                            row = sheet.getRow(j);
                            if (row != null) {
                                String wordPl = "";
                                String wordEn = "";
                                byte[] image = null;
                                for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
                                    cell = row.getCell((short) c);
                                    if (cell != null) {
                                        if (c == 0) {
                                            wordPl = cell.getStringCellValue();
                                        } else if (c == 1) {
                                            wordEn = cell.getStringCellValue();
                                        } else if (c == 2) {
                                            String path = MainActivity.PATH + cell.getStringCellValue();
                                            File imgFile = new File(path);

                                            if (imgFile.exists()) {
                                                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                myBitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
                                                image = stream.toByteArray();
                                            }
                                        }
                                    }
                                }
                                insertIntoWords(categoryId, wordPl, wordEn, image);
                            }
                        }
                        break;

                    case "Sentences":
                        category = sheetNameSplit[1];
                        categoryId = getCategoryId(category);

                        for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                            row = sheet.getRow(j);
                            if (row != null) {
                                String sentencePl = "";
                                String sentenceEn = "";
                                for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
                                    cell = row.getCell((short) c);
                                    if (cell != null) {
                                        if (c == 0) {
                                            sentencePl = cell.getStringCellValue();
                                        } else if (c == 1) {
                                            sentenceEn = cell.getStringCellValue();
                                        }
                                    }
                                }
                                insertIntoSentences(categoryId, sentencePl, sentenceEn);
                            }
                        }
                        break;

                    case "ExercisesTypes":
                        for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                            row = sheet.getRow(j);
                            if (row != null) {
                                for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
                                    cell = row.getCell((short) c);
                                    if (cell != null) {
                                        String typeName = cell.getStringCellValue();
                                        insertIntoExercisesTypes(typeName);
                                    }
                                }
                            }
                        }
                        break;

                    case "Exercises":
                        for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                            row = sheet.getRow(j);
                            if (row != null) {
                                String exerciseType = "";
                                int exerciseTypeId;

                                for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
                                    cell = row.getCell((short) c);
                                    if (cell != null) {
                                        if (c == 0) {
                                            category = cell.getStringCellValue();
                                        } else if (c == 1) {
                                            exerciseType = cell.getStringCellValue();
                                        }
                                    }
                                }
                                categoryId = getCategoryId(category);
                                exerciseTypeId = getExerciseTypeId(exerciseType);
                                insertIntoExercises(categoryId, exerciseTypeId);
                            }
                        }
                        break;

                    case "Questions":
                        for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                            row = sheet.getRow(j);
                            if (row != null) {
                                cell = row.getCell(0);
                                String question = cell.getStringCellValue();
                                insertIntoQuestions(question);
                            }
                        }
                        break;
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}
