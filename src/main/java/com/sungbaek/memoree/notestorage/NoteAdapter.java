package com.sungbaek.memoree.notestorage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sungbaek.memoree.R;
import com.sungbaek.memoree.notepad.Note;

import java.util.ArrayList;

/**
 * Customer adapter for displaying the notes in ListView
 * Display category, date, summary.
 * See note_item.xml for the item layout
 *
 */
public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, ArrayList<Note> noteList) { super(context, 0, noteList); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Note note = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_item, parent, false);
        }

        TextView category = (TextView) convertView.findViewById(R.id.item_category);
        TextView date = (TextView) convertView.findViewById(R.id.item_date);
        TextView summary = (TextView) convertView.findViewById(R.id.item_summary);

        category.setText(note.getCategory());
        date.setText(note.getDate());
        summary.setText(note.getSummary());
        return convertView;
    }
}
