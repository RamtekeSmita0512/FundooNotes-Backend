package fundoonotesapplication.service;

import java.util.List;


import fundoonotesapplication.dto.NotesDTO;

import fundoonotesapplication.model.Label;
import fundoonotesapplication.model.Notes;

public interface INotesService {

    Notes addNotes(NotesDTO notesDTO, String token);

    boolean updateNote(NotesDTO notesDTO, String token, long id);

    long trashNote(String token, long id);

    boolean deleteForever(String token, long id);

    long archive(String token, long id);

    List<Notes> getAllNote(String token);

    List<Label> allLabelofOneNote(String token, long id);
}
