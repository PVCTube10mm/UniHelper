import com.github.UniHelper.model.DefaultNotesModel;
import com.github.UniHelper.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

class DefaultNodesModelTest {
    DefaultNotesModel notesModel;

    @BeforeEach
    void createModel() {
        notesModel = new DefaultNotesModel();
    }

    @Test
    void saveFileShouldExist() {
        //Given
        String saveFileName = notesModel.getSaveFileName();
        File file = new File(saveFileName);

        //When
        boolean fileExists = file.isFile();

        //Then
        Assertions.assertTrue(fileExists);
    }

    @Test
    void afterAddNotesSizeShouldNotBeZero() {
        //Given
        Note note = new Note("title", "data");

        //When
        notesModel.addNote(note);

        //Then
        Assertions.assertNotEquals(0, notesModel.getAllNotes().size());
    }

    @Test
    void givenTwoEqualNotesBothShouldBePresent() {
        //Given
        Note note = new Note("", "");

        //When
        int sizeBeforeAddingNotes = notesModel.getAllNotes().size();
        notesModel.addNote(note);
        notesModel.addNote(note);
        int sizeAfterAddingNotes = notesModel.getAllNotes().size();

        //Then
        int expectedSize = sizeBeforeAddingNotes + 2;
        Assertions.assertEquals(expectedSize, sizeAfterAddingNotes);
    }

    @Test
    void whenUpdatingWithEmptyGetAllNotesShouldReturnEmpty() {
        //Given
        notesModel.updateNotes(new ArrayList<>());

        //When
        ArrayList<Note> returned = notesModel.getAllNotes();

        //Then
        Assertions.assertEquals(0, returned.size());
    }

    @Test
    void whenNoSuchNoteGetNoteByTitleShouldReturnNull() {
        //Given
        notesModel.updateNotes(new ArrayList<>());

        //When
        Note note1 = notesModel.getNoteByTitle("");
        Note note2 = notesModel.getNoteByTitle("title");

        //Then
        Assertions.assertNull(note1);
        Assertions.assertNull(note2);
    }

    @Test
    void OnlyNotesWithEqualTitleAndDataShouldBeEqual() {
        //Given
        Note n1 = new Note("", "");
        Note n2 = new Note("title", "");
        Note n3 = new Note("", "title");
        Note n4 = new Note("B", "A");
        Note n5 = new Note("B", "A");

        //When

        //Then
        Assertions.assertEquals(n1, n1);
        Assertions.assertEquals(n4, n5);
        Assertions.assertNotEquals(n1, n2);
        Assertions.assertNotEquals(n1, n3);
        Assertions.assertNotEquals(n2, n3);
        Assertions.assertNotEquals(n1, n4);
    }

    @Test
    void getAllNotesShouldReturnAllTheNotes() {
        //Given
        ArrayList<Note> notes = new ArrayList<>();
        Note n1 = new Note("title", "data");
        Note n2 = new Note("title", "data");
        Note n3 = new Note("title2", "data2");
        Note n4 = new Note("title2", "data3");
        notes.add(n1);
        notes.add(n2);
        notes.add(n3);
        notes.add(n4);

        //When
        notesModel.updateNotes(notes);
        notes.add(n1);
        notes.add(n2);
        notes.add(n3);
        notes.add(n4);
        ArrayList<Note> returned = notesModel.getAllNotes();

        //Then
        for (Note n : notes)
            Assertions.assertTrue(returned.contains(n));

    }

    @Test
    void afterUpdateNotesShouldHaveOnlyGivenNotes() {
        //Given
        Note noteBefore1 = new Note("title", "data");
        Note noteBefore2 = new Note("title2", "data2");
        Note noteBefore3 = new Note("title3", "data3");
        notesModel.addNote(noteBefore1);
        notesModel.addNote(noteBefore2);
        notesModel.addNote(noteBefore3);
        ArrayList<Note> notes = new ArrayList<>();
        Note noteAfter1 = new Note("title4", "data4");
        Note noteAfter2 = noteBefore2;
        Note noteAfter3 = new Note("title", "data2");
        notes.add(noteAfter1);
        notes.add(noteAfter2);
        notes.add(noteAfter3);

        //When
        notesModel.updateNotes(notes);
        ArrayList<Note> afterUpdate = notesModel.getAllNotes();

        //Then
        boolean hasNoteBefore1 = afterUpdate.contains(noteBefore1);
        boolean hasNoteBefore2 = afterUpdate.contains(noteBefore2);
        boolean hasNoteBefore3 = afterUpdate.contains(noteBefore3);
        boolean hasNoteAfter1 = afterUpdate.contains(noteAfter1);
        boolean hasNoteAfter2 = afterUpdate.contains(noteAfter2);
        boolean hasNoteAfter3 = afterUpdate.contains(noteAfter3);

        Assertions.assertFalse(hasNoteBefore1);
        Assertions.assertTrue(hasNoteBefore2);
        Assertions.assertFalse(hasNoteBefore3);
        Assertions.assertTrue(hasNoteAfter1);
        Assertions.assertTrue(hasNoteAfter2);
        Assertions.assertTrue(hasNoteAfter3);
    }

    @Test
    void getNoteByTitleShouldReturnCorrectNote() {
        //Given
        notesModel.updateNotes(new ArrayList<>());
        Note n1 = new Note("", "");
        Note n2 = new Note("title", "");
        Note n3 = new Note("", "title");
        Note n4 = new Note("A", "B");
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(n1);
        notes.add(n2);
        notes.add(n3);
        notes.add(n4);
        notesModel.updateNotes(notes);

        //When
        Note r1 = notesModel.getNoteByTitle("null");
        Note r2 = notesModel.getNoteByTitle("");
        Note r3 = notesModel.getNoteByTitle("title");
        Note r4 = notesModel.getNoteByTitle("A");

        //Then
        Assertions.assertNull(r1);
        Assertions.assertEquals(n1, r2);
        Assertions.assertEquals(n2, r3);
        Assertions.assertEquals(n4, r4);
    }
}
