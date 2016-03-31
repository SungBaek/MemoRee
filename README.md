# MemoRee

Goal :
The goal of the application is to solve the user need in the occasions where the user wants to write down quick notes, but does 
not have sufficient time to flesh out ideas or even write down a single word. User has an option to create a "MemoRee key," which
serves as a tool in mnemotic technique to allow easier memory retrievation. User can flesh out the MemoRee keys at later times
when he or she has the time to do so. 

Architectural Summary :
Notes are stored in the SQLitedatabase and retrieved through the DatabaseConnector class, which extends SQLiteOpenHelper.
Notes can be browsed in the NoteStorageActivity and NoteAdapter handles the conversion from native container of notes to a ListView.
Notes are created in the NotePadActivity where the user can type in inputs through EditText views. Notes are stored through
  DatbaseConnector class.

Todos:
 Add polished UI
 Allow users to create a MemoRee key with one click of a button
