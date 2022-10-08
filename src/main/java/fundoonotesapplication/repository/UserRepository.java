package fundoonotesapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fundoonotesapplication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    @Query(value="Select * from user where email = :email",nativeQuery = true)
    User findEmail(String email);

    @Query(value = "select * from user where user_id = :userId", nativeQuery = true)
    User findbyId(long userId);

    @Modifying
    @Query(value="Insert into user_model (email,firstname,is_verified,lastname,password,username) values (:email,:firstname,:is_verified,:lastname,:password,:username)",nativeQuery = true)
    void insertdata( String email, String firstname, boolean is_verified, String lastname, String password, String username);

    @Modifying
    @Query(value="update user set is_verified = true where user_id = :userId", nativeQuery = true)
    User verify(long userId);
}