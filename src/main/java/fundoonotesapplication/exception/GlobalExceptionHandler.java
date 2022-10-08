package fundoonotesapplication.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import fundoonotesapplication.dto.ResponseDTO;


public class GlobalExceptionHandler {
	 private static final String message = "Exception While Processing REST Request";

	    @org.springframework.web.bind.annotation.ExceptionHandler(GlobalException.class)
	    public ResponseEntity<ResponseDTO> handleUserRegistrationException(GlobalException exception) {
	        ResponseDTO responseDTO = new ResponseDTO(message, exception.getMessage());
	        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.BAD_REQUEST);
	    }

	    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
	    public ResponseEntity<ResponseDTO> handelHttpMessageNotReadableException(
	            HttpMessageNotReadableException exception) {
	        ResponseDTO responseDTO = new ResponseDTO(message, "Should have date in dd MM yyyy format");
	        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
	    }

	    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(
	            MethodArgumentNotValidException exception) {
	        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
	        List<String> errMesg = errorList.stream().map(objErr -> objErr.getDefaultMessage())
	                .collect(Collectors.toList());
	        ResponseDTO responseDTO = new ResponseDTO(message, errMesg);
	        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
	    }

	}


