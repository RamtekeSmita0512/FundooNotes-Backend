package fundoonotesapplication.dto;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    public String firstName;
    public  String lastName;
    public String email;
    public String password;
    public String address;
    public String username;
}
