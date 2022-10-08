package fundoonotesapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesDTO {

    private String title;
    private String description;
    private boolean isArchived;
    private boolean isDeleted;;
}

