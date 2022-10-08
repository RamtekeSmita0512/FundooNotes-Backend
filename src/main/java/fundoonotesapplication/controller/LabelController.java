package fundoonotesapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import fundoonotesapplication.dto.LabelDTO;
import fundoonotesapplication.dto.ResponseDTO;
import fundoonotesapplication.model.Label;
import fundoonotesapplication.service.ILabelService;


@RestController
public class LabelController {

    @Autowired
    private ILabelService iLabelService;

    @PostMapping("/addlabel")
    public ResponseEntity<ResponseDTO> createNewLabel(@RequestBody LabelDTO labelDTO,  @RequestHeader("token") String token) {
        int result = iLabelService.createLabel(labelDTO, token);
        if (result != 0)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Label is Created", 200));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Already exist in label ", 400));
    }


    @GetMapping("/getlabel")
    public ResponseEntity<ResponseDTO> getAllLabels(@RequestHeader("token") String token) {
        List<Label> labelList = iLabelService.getAllLabel(token);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(200, "all labels of user", labelList));
    }

    @PutMapping("/updatelabel/{labelId}")
    public ResponseEntity<ResponseDTO> updateLabel(@RequestBody long labelId,@RequestBody LabelDTO labelDTO, @RequestHeader("token") String token) {

        boolean result = iLabelService.updateLabel(labelDTO,labelId, token);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Label is updated", 200));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Something went wrong", 400));
    }

    @DeleteMapping("/label/{labelId}")
    public ResponseEntity<ResponseDTO> deleteLabelById(@PathVariable long labelId, @RequestHeader("token") String token) {

        boolean result = iLabelService.deleteLabelById(labelId, token);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Label is deleted", 200));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Something went wrong", 400));
    }

    @GetMapping("/alllabel")
    public ResponseEntity<ResponseDTO> getAllLabel(@RequestHeader("token") String token)
    {
        List<Label> labelList = iLabelService.getAllLabel(token);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(200, "all labels of user", labelList));
    }
    
}
