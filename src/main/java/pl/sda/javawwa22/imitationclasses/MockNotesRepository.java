package pl.sda.javawwa22.imitationclasses;

import java.util.*;

public class MockNotesRepository implements NotesRepository {

    //symulujemy 'baze danych' w pamieci - potrzebujemy kontenera na dane typu
    //fullName - List<Note>
    private Map<String, List<Note>> nameToNotesMap = new HashMap<>();

    @Override
    public void save(Note note) {
        List<Note> notes;
        if(nameToNotesMap.containsKey(note.getFullName())) {
            //druga i kolejna ocena
            notes = nameToNotesMap.get(note.getFullName());
        }
        else {
            //pierwsza ocena tego goscia
            notes = new ArrayList<>();
        }
        notes.add(note);
        nameToNotesMap.put(note.getFullName(), notes);
    }

    //zwracamy pusta liste jesli nie ma takiego studenta
    @Override
    public List<Note> getAllNotesOf(String fullName) {
        if(nameToNotesMap.containsKey(fullName)) {
            return nameToNotesMap.get(fullName);
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public void removeAll() {
        nameToNotesMap.clear();
    }
}
