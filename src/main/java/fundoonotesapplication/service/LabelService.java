package fundoonotesapplication.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fundoonotesapplication.dto.LabelDTO;
import fundoonotesapplication.model.Label;
import fundoonotesapplication.model.User;
import fundoonotesapplication.repository.LabelRepository;
import fundoonotesapplication.repository.NotesRepository;
import fundoonotesapplication.repository.UserRepository;
import fundoonotesapplication.utility.TokenUtil;



@Service
public class LabelService implements ILabelService{

    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public int createLabel(LabelDTO labelDTO, String token) {
        long userId = tokenUtil.decodeToken(token);
        Optional<User> isUserAvailable = userRepository.findById(userId);
        if(isUserAvailable != null)
        {
            String labelName = labelDTO.getLabelTitle();
            Label label = labelRepository.findByName(labelName);
            if(label == null)
            {
                return labelRepository.insertLabelData(labelDTO.getLabelTitle(), userId);

            }
        }
        return 0;
    }

    @Override
    public boolean updateLabel(LabelDTO labelDTO, long labelId , String token) {
        long userId = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(userId);
        if(user != null)
        {
            Label label = labelRepository.findById(labelId, userId);
            if(label != null)
            {
                label.setLabelTitle(labelDTO.getLabelTitle());
                labelRepository.update(label.getLabelTitle(), labelId);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteLabelById(long labelId, String token) {
        long userId = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(userId);
        if(user != null)
        {
            Label label = labelRepository.findById(labelId, userId);
            if(label != null)
            {
                labelRepository.delete(userId, labelId);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Label> getAllLabel(String token) {
        long userId = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(userId);
        if(user != null)
        {
            List<Label> labeldata = labelRepository.getAll(userId);
            return labeldata;
        }
        return null;
    }

//    @Override
//    public Label createOrMapWithNote(LabelDTO labelDTO, long notesId, String token) {
//        long userId = tokenUtil.decodeToken(token);
//        Optional<User> user = userRepository.findById(userId);
//        if( user != null)
//        {
//            Notes noteInfo = notesRepository.findById(notesId);
//            if(noteInfo != null)
//            {
//                String labelTitle = labelDTO.getLabelTitle();
//                Label label = labelRepository.findByTitle(labelTitle);
//                if(label == null)
//                {
//                    Label newLabel = new Label();
//                    newLabel.setLabelTitle(labelDTO.getLabelTitle());
//                    labelRepository.insertLabelData(newLabel.getLabelTitle(), userId);
//                    Label labelCreate = labelRepository.findByName(newLabel.getLabelTitle());
//                    labelRepository.insertdatatomap(notesId, labelCreate.getLabelId());
//                    return labelCreate;
//                }
//                else
//                {
//                    Object map = labelRepository.findoneByLabelIdAndNoteId(label.getLabelId(), notesId);
//                    if (map == null) {
//                        labelRepository.insertdatatomap(notesId, label.getLabelId());
//                    }
//                    return label;
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Label addLabelsToNote(String token, long labelId, long notesId) {
//        long userId = tokenUtil.decodeToken(token);
//        Optional<User> user = userRepository.findById(userId);
//        if(user != null)
//        {
//            Notes note = notesRepository.findById(notesId);
//            if(note != null)
//            {
//                Label isLabelAvailable = labelRepository.findById(labelId, userId);
//                if(isLabelAvailable != null)
//                {
//                    Object label = labelRepository.findoneByLabelIdAndNoteId(isLabelAvailable.getLabelId(), noteId);
//                    if(label == null)
//                    {
//                        labelRepository.insertdatatomap(notesId, isLabelAvailable.getLabelId());
//                    }
//                    return isLabelAvailable;
//                }
//            }
//        }
//        return null;
//    }
}
