package com.sungbaek.memoree.notepad;

import java.util.Date;

/**
 * Note class represents the note that user saves
 * dateModified is modified everytime the note is modified and saved
 * Color represents the quickCategory
 * TODO : add quick color functionality
 */
public class Note {

    private final static int SUMMARY_LENGTH = 45;

    public enum Color {
        BLUE("blue"), ORANGE("orange"), RED("red"), PURPLE("purple"), GREEN("green"), YELLOW("yellow");
        public String val;

        private Color(String i) {
            this.val = i;
        }
    }

    private long id;
    private String note;
    private String dateModified;
    private String category;
    private boolean saved;

    public Note(long id, String note, String dateModified, String category) {
        this.id = id;
        this.note = note;
        this.dateModified = dateModified;
        this.category = category;
        this.saved = false;
    }

    //functions
    public String getNote() {
        return this.note;
    }

    public String getDate() {
        return this.dateModified;
    }

    public String getCategory() {
        return this.category;
    }

    public String getSummary() {
        if (this.note.length() < SUMMARY_LENGTH)
            return this.note;
        else
            return this.note.substring(0, SUMMARY_LENGTH) + "...";
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date){
        this.dateModified = date;
    }
    public boolean isSaved() {
        return this.saved;
    }

    public void setSaved() {
        this.saved = true;
    }
}
