package com.sungbaek.memoree.notepad;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sungbaek.memoree.DatabaseConnector;
import com.sungbaek.memoree.MemoRee;
import com.sungbaek.memoree.R;

public class NotepadActivity extends AppCompatActivity {
    DatabaseConnector db;
    Note note;

    /**
     * public void saveNote()
     * Create new note and save, if the note is new
     * Update the existing note, if the note is already saved in the database
     */
    public void saveNote() {
        if (note.isSaved()) {
            updateNote();
        } else {
            createNewNote();
        }
    }

    /**
     * public void CreateNewNote()
     * Create new note by using the DatabaseConnector
     * Retrieve noteString, modified date, and category from notepad layout and save them
     */
    public void createNewNote() {
        String noteString = ((EditText) findViewById(R.id.memopad)).getText().toString();
        String modified = DatabaseConnector.getDateNow();
        String category = ((EditText) findViewById(R.id.memopad_category)).getText().toString();

        this.note = new Note(-1, noteString, modified, category);
        this.note.setId(db.saveNote(this.note));
        Toast.makeText(this, "note saved " + this.note.getId(), Toast.LENGTH_SHORT).show();
    }

    /**
     * public void updateNote()
     * Update the existing note with new text, dateModified, and category.
     * Retrieve the updated parameters from the notepad layout
     */
    public void updateNote() {
        String noteString = ((EditText) findViewById(R.id.memopad)).getText().toString();
        String modified = DatabaseConnector.getDateNow();
        String category = ((EditText) findViewById(R.id.memopad_category)).getText().toString();

        note.setNote(noteString);
        note.setDate(modified);
        note.setCategory(category);

        db.updateNote(note);
        Toast.makeText(this, "note updated", Toast.LENGTH_SHORT).show();
    }

    /**
     * public void changeColor(View v)
     * development tool for testing the functionality of changing views colors dynamically
     * @param v the clicked view
     */
    public void changeColor(View v) {
        TextView tv = (TextView) findViewById(R.id.color_box);
        tv.setBackgroundColor(Color.parseColor(Note.Color.BLUE.val));
        tv.invalidate();
        Toast.makeText(this, "changed color!", Toast.LENGTH_SHORT).show();
    }

    /**
     * protected void onCreate(Bundle savedInstanceState)
     * Checks the intent to determine if the activity is opening a new note, or an existing note
     * If the new note is opened, initialize a dummy note variable
     * If the existing note is opened, retrieve the data from intentExtra and construct the new note
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        //check intent to see if it's a new note or a existing note
        Intent intent = getIntent();
        db = ((MemoRee) getApplication()).getDatabase();

        if (intent.getBooleanExtra("defined", false)) {
            this.note = new Note(-1, "", "new", "none");
        } else {
            String note = intent.getStringExtra("note");
            String category = intent.getStringExtra("category");
            String date = intent.getStringExtra("date");
            int id = intent.getIntExtra("id", -1);

            this.note = new Note(id, note, date, category);
        }


        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notepad, menu);
        return true;
    }

    /**
     * Add save_icon and perform saveNote() when pressed
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.save_icon) {
            saveNote();
        }

        return super.onOptionsItemSelected(item);
    }
}
