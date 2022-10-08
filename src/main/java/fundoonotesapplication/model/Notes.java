package fundoonotesapplication.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notesId;
    private String title;
    private String description;
    @Column(columnDefinition = "boolean default false")
    private boolean isArchived;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
    private boolean isTrashed;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany
    @JsonIgnore
    private List<Label> labels;

    public Notes(String title, String description, boolean isArchived, boolean isDeleted) {
        this.title = title;
        this.description = description;
        this.isArchived = isArchived;
        this.isDeleted = isDeleted;
    }

    
}