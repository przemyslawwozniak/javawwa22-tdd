package pl.sda.javawwa22.imitationclasses;

import java.util.List;

public class MockNotesRepository implements NotesRepository {



    @Override
    public void save(Note note) {

    }

    @Override
    public List<Note> getAllNotesOf(String fullName) {
        return null;
    }

    @Override
    public void removeAll() {

    }
}
