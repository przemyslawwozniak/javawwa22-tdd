package pl.sda.javawwa22.imitationclasses;

import org.junit.Test;
import pl.sda.javawwa22.imitationclasses.exceptions.NoSuchUserException;

import java.util.List;

import static org.junit.Assert.*;

public class NotesServiceTest {

    NotesRepository notesRepository = new MockNotesRepository();
    NotesService notesService = DefaultNotesService.createWith(notesRepository);

    @Test(expected = IllegalArgumentException.class)
    public void add_null_note() {
        notesService.add(null);
    }

    @Test
    public void add() {
        //given:
        Note note = Note.of("PW", 5.0);

        //when:
        notesService.add(note);

        //then:
        List<Note> pwNotes = notesRepository.getAllNotesOf("PW");
        assertNotNull(pwNotes);
        assertFalse(pwNotes.isEmpty());
        assertEquals(1, pwNotes.size());
        assertEquals(5.0, pwNotes.get(0).getScore(), 0.00001);
        assertEquals("PW", pwNotes.get(0).getFullName());
    }

    @Test(expected = NoSuchUserException.class)
    public void average_of_nonexistent() {
        //given:

        //when:
        notesService.averageOf("PW");

        //then:
        //...throws exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void average_of_null() {
        notesService.averageOf(null);
    }

    @Test
    public void average_of_existing() {
        //given:
        Note note = Note.of("PW", 5.0);
        Note note2 = Note.of("PW", 3.0);

        notesService.add(note);
        notesService.add(note2);

        //when:
        double avgMarkOfPW = notesService.averageOf("PW");

        //then:
        assertEquals(4.0, avgMarkOfPW, 0.0001);
    }

    //TestNG ma adnotacje 'dependsOn', w zwiazku z czym mozemy miec 100% pewnosci odnosnie 'add'
    @Test
    public void clear() {
        //given:
        Note note = Note.of("PW", 5.0);
        //zakladam ze to dziala wg intencji, ale wolalbym miec 'dependsOn'
        notesService.add(note);

        //when:
        notesService.clear();

        //then:
        List<Note> pwNotes = notesRepository.getAllNotesOf("PW");
        assertTrue(pwNotes.isEmpty());
    }

}
