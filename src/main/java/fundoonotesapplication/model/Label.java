package fundoonotesapplication.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long labelId;
    private String labelTitle;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userLabel;

    @ManyToMany(mappedBy = "labels")
    @JsonIgnore
    private List<Notes> listnote;

    public Label(long labelId, String labelTitle) {
        this.labelId = labelId;
        this.labelTitle = labelTitle;
    }

//    public Label(long labelId, String labelTitle, User user, List<Notes> listNote) {
//        this.labelId = labelId;
//        this.labelTitle = labelTitle;
//        this.user = user;
//        this.listNote = listNote;
//    }
}