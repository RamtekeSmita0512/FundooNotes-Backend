package fundoonotesapplication.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;
import fundoonotesapplication.dto.LoginDTO;
import fundoonotesapplication.dto.ResponseDTO;
import fundoonotesapplication.dto.UserDTO;
import fundoonotesapplication.model.User;
import fundoonotesapplication.service.IUserService;
import fundoonotesapplication.utility.EmailService;
import fundoonotesapplication.utility.TokenUtil;


@RestController
//@CrossOrigin(allowedHeaders = "*", origins = "*", exposedHeaders = { "jwtToken" })
public class UserController {
  @Autowired
  IUserService iUserService;

  @Autowired
  TokenUtil tokenUtil;
  @Autowired
  private EmailService emailService;

  List<User> userList= new ArrayList<>();

  @GetMapping("/getalluser")
  public ResponseEntity<ResponseDTO> getUserData() {
      userList = iUserService.getUserData();
      ResponseDTO respOTO = new ResponseDTO("Get Call Successful", userList);
      return new ResponseEntity<ResponseDTO>(respOTO, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<ResponseDTO> createUserData(@RequestBody UserDTO userDTO) {
      User user = iUserService.register(userDTO);
      if (user != null) {

          return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(200, "registration successfull", user));
      } else {
          return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                  .body(new ResponseDTO(208, "user already exist", userDTO));
      }
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {
      User userInformation = iUserService.login(loginDTO);
      if (userInformation != null)
          return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(200, tokenUtil.createToken(userInformation.getId()), userInformation));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Login failed", 400));
  }

  @PutMapping("/updateUser/{token}")
  public ResponseEntity<ResponseDTO> updateUserData(@PathVariable String token,@RequestBody UserDTO userDTO) {
      ResponseDTO respDTO= new ResponseDTO("Updated User Details Successfully", iUserService.updateUserData(token,userDTO));
      return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
  }

  @DeleteMapping("/deleteUser/{token}")
  public ResponseEntity <ResponseDTO> deleteUserData(@PathVariable String token) {
      iUserService.deleteUserData(token);
      ResponseDTO respDTO= new ResponseDTO("Deleted Successfully", "Deleted id: "+token);
      return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
  }

  @GetMapping("/verify/{token}")
  public ResponseEntity<ResponseDTO> userVerification(@PathVariable("token") String token) {
      User user = iUserService.verify(token);
      if (user != null) {
          return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("verified", 200));
      }
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDTO("not verified", 304));

  }

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}


}
