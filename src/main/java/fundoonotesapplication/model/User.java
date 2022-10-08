package fundoonotesapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import fundoonotesapplication.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private  String lastname;
    private String email;
    private String password;
    private String address;
    private  String username;
    private String token;
    private boolean verify = false;
    @Column(columnDefinition = "boolean default false")
    private boolean isVerified;

    public User(UserDTO userDTO){
        this.firstName=userDTO.firstName;
        this.lastname=userDTO.lastName;
        this.email=userDTO.email;
        this.password=userDTO.password;
        this.address=userDTO.address;
        this.username=userDTO.username;
    }

    public User(int id,UserDTO userDTO){
        this.id=id;
        this.firstName=userDTO.firstName;
        this.lastname=userDTO.lastName;
        this.email=userDTO.email;
        this.password=userDTO.password;
        this.address=userDTO.address;
        this.username=userDTO.username;
    }


//    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    private List<Notes>  notesList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Label> labelList = new ArrayList<>();

//    public User(String email, String user_is_registered, String s) {
//    }
}
