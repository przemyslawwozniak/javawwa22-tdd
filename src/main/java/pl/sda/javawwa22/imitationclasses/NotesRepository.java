package pl.sda.javawwa22.imitationclasses;

import java.util.List;

public interface NotesRepository {

    void save(Note note);   //command
    List<Note> getAllNotesOf(String fullName);  //query
    void removeAll();   //command

}

