package com.sungbaek.memoree;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;

import android.widget.Toast;

import com.sungbaek.memoree.notepad.NotepadActivity;
import com.sungbaek.memoree.notestorage.NoteStorageActivity;

/**
 * Development activity for testing functionalities
 * Open various activities to test them
 *
 */
public class MainActivity extends AppCompatActivity {

    public void die(View v){
        Toast.makeText(this, "You are exiting", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void openNoteList(View v){
        Intent i = new Intent(this, NoteStorageActivity.class);
        startActivity(i);
    }

    public void openMemopad(View v) {
        Intent i = new Intent(this, NotepadActivity.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View v;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
