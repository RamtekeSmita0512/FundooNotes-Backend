package fundoonotesapplication.service;

import java.util.List;


import fundoonotesapplication.dto.LabelDTO;
import fundoonotesapplication.model.Label;

public interface ILabelService {

    public int createLabel(LabelDTO labelDTO ,String token);

    public boolean updateLabel(LabelDTO labelDTO,long labelId , String token);

    public boolean deleteLabelById(long labelId, String token);

    List<Label> getAllLabel(String token);

//    Label createOrMapWithNote(LabelDTO labelDTO, long notesId, String token);
//
//    Label addLabelsToNote(String token, long labelId, long notesId);
}