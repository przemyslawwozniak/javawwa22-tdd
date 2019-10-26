package pl.sda.javawwa22.imitationclasses;

import org.junit.Test;

public class NotesServiceTest {

    NotesService notesService = DefaultNotesService.createWith(new MockNotesRepository());

    @Test(expected = IllegalArgumentException.class)
    public void add_null_note() {
        notesService.add(null);
    }

    @Test
    public void add() {
        
    }

    @Test
    public void average_of_null() {

    }

    @Test
    public void average_of_nonexistent() {

    }

    @Test
    public void average_of_existing() {

    }

    @Test
    public void clear() {

    }

}
