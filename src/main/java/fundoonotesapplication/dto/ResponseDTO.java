package fundoonotesapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private int status;
    private String message;
    private Object data;

    public ResponseDTO(String message,int status) {
        this.message = message;
        this.status = status;
    }

    public ResponseDTO(int status, String message, Object data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}