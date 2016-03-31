package com.sungbaek.memoree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.sungbaek.memoree.notepad.Note;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * sqlite database helper
 * connects to Android's SQLitedatabase
 * can get all the notes, save/update individual notes
 * TODO: Remove a note, remove all notes
 */
public final class DatabaseConnector extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "memoree";

    public static final int DATABASE_VERSION = 1;

    public static final String NOTE_TABLE = "notes";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_CATEGORY = "category";
    public static final String COLUMN_NAME_MODIFIED = "modified";
    public static final String COLUMN_NAME_NOTE = "note";

    public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    //create statements
    public static final String CREATE_NOTES = "CREATE TABLE IF NOT EXISTS " + NOTE_TABLE + "("
            + COLUMN_NAME_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_NAME_CATEGORY + " TEXT, "
            + COLUMN_NAME_MODIFIED + " DATETIME, "
            + COLUMN_NAME_NOTE + " TEXT" +
            ")";

    public static final String DROP_NOTES = "DROP TABLE IF EXISTS " + NOTE_TABLE;


    public DatabaseConnector(Context context) {
        super(context, DatabaseConnector.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_NOTES);
        onCreate(db);
    }

    //CRUD Operations

    /**
     * Retrieve all the notes from SQLitedatabase by executing query
     * "SELECT * FROM Notes" and accessing each element with cursor
     * then storing each element as a Note in List<Note>
     *
     * @param null
     * @return List all the notes in the database
     */
    public ArrayList<Note> getNotes() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Note> noteList = new ArrayList<Note>();
        String selectQuery = "SELECT * FROM " + NOTE_TABLE;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {

                int noteId = c.getInt(c.getColumnIndex(COLUMN_NAME_ID));
                String noteText = c.getString(c.getColumnIndex(COLUMN_NAME_NOTE));
                String noteCategory = c.getString(c.getColumnIndex(COLUMN_NAME_CATEGORY));
                String noteModified = c.getString(c.getColumnIndex(COLUMN_NAME_MODIFIED));

                Note note = new Note(noteId, noteText, noteModified, noteCategory);
                noteList.add(note);
            } while (c.moveToNext());
        }
        db.close();
        return noteList;
    }

    /**
     * Update a single note with the given Note class in SQLitedatabase
     * Update the note with the same id inside the sqlite database
     *
     * @param Note the target of updating Note
     * @return void
     */
    public void updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_MODIFIED, note.getDate());
        values.put(COLUMN_NAME_NOTE, note.getNote());
        values.put(COLUMN_NAME_CATEGORY, note.getCategory());

        String selection = COLUMN_NAME_ID + " =?";
        String[] selectionArgs = {String.valueOf(note.getId())};

        db.update(NOTE_TABLE, values, selection, selectionArgs);

        db.close();
    }

    /**
     * Create a new note and save it to SQLitedatabase, pass null value as key so the
     * database autogenerates a long key for the note
     *
     * @param Note the new note you want to save, its id can be anything
     * @return long new id for the note
     */
    public long saveNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_CATEGORY, note.getCategory());
        values.put(COLUMN_NAME_MODIFIED, note.getDate());
        values.put(COLUMN_NAME_NOTE, note.getNote());

        long newId = db.insert(NOTE_TABLE, null, values);
        note.setId(newId);
        note.setSaved();
        db.close();
        return newId;
    }

    /**
     * Handles the conversion of Date object to String
     * helper function
     *
     * @param null
     * @return String DateTime of now in the format of yyyy/MM/dd HH:mm:ss in String
     */
    public static String getDateNow() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(Calendar.getInstance().getTime());
    }
}
