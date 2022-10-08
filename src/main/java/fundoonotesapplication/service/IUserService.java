package fundoonotesapplication.service;

import java.util.List;

import fundoonotesapplication.dto.LoginDTO;

import fundoonotesapplication.dto.UserDTO;
import fundoonotesapplication.model.User;



	public interface IUserService {


	    List<User> getUserData();

	    User getUserDataById(String token);

	    User register(UserDTO userDTO);

	    User login(LoginDTO loginDTO);

	    User verify(String token);

	    User updateUserData(String token, UserDTO userDTO);

	    String deleteUserData(String token);

	    String Search(LoginDTO loginDTO);
	}
