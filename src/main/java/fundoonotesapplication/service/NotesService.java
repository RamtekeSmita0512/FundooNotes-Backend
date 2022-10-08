package fundoonotesapplication.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fundoonotesapplication.dto.NotesDTO;
import fundoonotesapplication.model.Label;
import fundoonotesapplication.model.Notes;
import fundoonotesapplication.model.User;
import fundoonotesapplication.repository.NotesRepository;
import fundoonotesapplication.repository.UserRepository;
import fundoonotesapplication.utility.TokenUtil;



@Service
public class NotesService implements INotesService{
    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Notes addNotes(NotesDTO notesDTO, String token) {
        long id = tokenUtil.decodeToken(token);
        User user = userRepository.findbyId(id);
        if (user != null) {
//            Notes note = new Notes(notesDTO.getTitle(), notesDTO.getDescription(),
//                    notesDTO.isArchived(), notesDTO.isDeleted());
//            note.setUser(user);
//            notesRepository.insertData(note.getNotesId(),note.getDescription(), note.getTitle(),
//                    note.isArchived(), note.isDeleted());
//            notesRepository.save(note);
//            return note;
            Notes notes = modelMapper.map(notesDTO,Notes.class);
            notesRepository.save(notes);
            return notes;
        }
        return null;
    }
    @Override
    public boolean updateNote(NotesDTO notesDTO, String token, long id) {
        long userId = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(userId);
        if (user != null) {
            Notes note = notesRepository.findById(id);
            note.setDescription(notesDTO.getDescription());
            note.setTitle(notesDTO.getTitle());
//            notesRepository.updateData(note.getDescription(), note.getTitle(),id, id);
            notesRepository.save(note);
            return true;
        }
        return false;
    }

    @Override
    public long trashNote(String token, long id) {
        long userId = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(userId);
        if (user != null) {
            Notes note = notesRepository.findById(id);
            if (note.isDeleted()) {
                return notesRepository.delete(false, userId, id);
            } else {
                notesRepository.delete(true, userId, id);
                return 0;
            }
        }
        return -1;
    }

    @Override
    public boolean deleteForever(String token, long id) {
        long userId = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(userId);
        if (user != null) {
            Notes note = notesRepository.findById(id);

            if (note.isDeleted()) {
                notesRepository.deleteForever(userId, id);
                return true;
            }
        }
        return false;
    }

    @Override
    public long archive(String token, long id) {
        long userId = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(userId);
        if (user != null) {
            Notes notes = notesRepository.findById(id);
            if (notes.isArchived()) {
                notesRepository.setArchive(false, userId, id);
                return 1;
            } else if (!notes.isArchived()) {
                notesRepository.setArchive(true, userId, id);
                return 0;
            } else {
                return -1;
            }
        }
        return -1;
    }

    @Override
    public List<Notes> getAllNote(String token) {
        long userId = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(userId);
        if (user != null) {
            List<Notes> notes = notesRepository.getAll(userId);
            return notes;
        }
        return null;
    }
    @Override
    public List<Label> allLabelofOneNote(String token, long id) {
        long userId = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(userId);
        if(user != null)
        {
            Notes note = notesRepository.findById(id);
            if(note != null)
            {
                List<Label> label = notesRepository.findById(id).getLabels();
                return label;
            }
        }
        return null;
    }
}
