package fundoonotesapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import fundoonotesapplication.dto.NotesDTO;
import fundoonotesapplication.dto.ResponseDTO;
import fundoonotesapplication.model.Label;
import fundoonotesapplication.model.Notes;
import fundoonotesapplication.service.INotesService;
import fundoonotesapplication.utility.TokenUtil;


@RestController
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private INotesService iNotesService;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping("/create")
    private ResponseEntity<ResponseDTO> createNote(@RequestBody NotesDTO notesDTO, @RequestHeader("token") String token)
            throws Exception {
        Notes note = iNotesService.addNotes(notesDTO,token);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(200, "Note is created successfully", note));

    }

    @PutMapping("/updatenote/{id}")
    public ResponseEntity<ResponseDTO> updateNote(@RequestBody NotesDTO notesDTO, @RequestHeader("token") String token,
                                               @PathVariable("id") long id) {
        boolean result = iNotesService.updateNote(notesDTO, token, id);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Note is update successfully", 200));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Something went wrong", 400));
        }
    }
    @PostMapping("/deleteORrestore/{id}")
    public ResponseEntity<ResponseDTO> deleteNote(@RequestHeader("token") String token, @PathVariable("id") long id) {

        long result = iNotesService.trashNote(token, id);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("successfully restored", 200));
        } else if (result == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("successfully deleted", 200));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Something went wrong", 400));
        }
    }

    @PostMapping("/archive/{id}")
    private ResponseEntity<ResponseDTO> archive(@PathVariable("id") long id, @RequestHeader("token") String token) {

        long result = iNotesService.archive(token, id);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("succussfully removed from Archive", 200));
        } else if (result == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("succussfully Archived", 200));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Something went wrong", 400));
        }
    }

    @PostMapping("/allnotes")
    public ResponseEntity<ResponseDTO> getAllNotes(@RequestHeader("token") String token)  {
        List<Notes> notesList = iNotesService.getAllNote(token);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(200, "all notes of user", notesList));
    }

    @GetMapping("/getnotelabels")
    public ResponseEntity<ResponseDTO> getAllNoteLabels(@RequestHeader("token") String token,
                                                     @RequestParam("noteId") long noteId) {
        List<Label> noteList = iNotesService.allLabelofOneNote(token, noteId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO( "Labels related to current Note are", noteList));
    }

    @PostMapping("/deleteforever/{id}")
    public ResponseEntity<ResponseDTO> deleteNoteForever(@RequestHeader("token") String token,
                                                      @PathVariable("id") long id) {
        boolean result = iNotesService.deleteForever(token, id);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("succussfully deleted", 200));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("Something went wrong can't delete", 400));
        }

    }


}
