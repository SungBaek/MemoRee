package com.sungbaek.memoree.notestorage;

import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.sungbaek.memoree.DatabaseConnector;
import com.sungbaek.memoree.MemoRee;
import com.sungbaek.memoree.R;
import com.sungbaek.memoree.notepad.Note;

import java.util.ArrayList;
/*
* Activity for listing the notes
* Notes can be pressed to be opened and edited
* TODO : Organize notes by dates and categories
*
 */
public class NoteStorageActivity extends AppCompatActivity {

    private DatabaseConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_storage);

        db = new DatabaseConnector( this );
        //arraylist test
        ArrayList<Note> tests = new ArrayList<>();
        Note note = new Note(20, "hello", "10/12/13", "none");
        tests.add(note);

        ArrayList<Note> notes = db.getNotes();
        final NoteAdapter adapter = new NoteAdapter(this, notes);
        ListView listView = (ListView)findViewById(R.id.note_list);
        listView.setAdapter(adapter);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_storage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
