package fundoonotesapplication.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Data
@Component
public class MailObject implements Serializable {

    private static final long serialVersionUID = 1L;
    String email;
    String subject;
    String message;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
