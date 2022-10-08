package fundoonotesapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fundoonotesapplication.model.Label;


@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {

    @Query(value = "select * from label where label_title = :labelName", nativeQuery = true)
    Label findByName(String labelName);

    @Modifying
    @Query(value = "insert into label (label_title, user_id) values (:labelTitle, :userId)", nativeQuery = true)
    int insertLabelData(String labelTitle, long userId);

//    @Query(value = "select * from label  where label_title = :labelTitle", nativeQuery = true)
//    Label findByTitle(String labelTitle);

    @Query(value = "select * from label where label_id = :labelId and user_id =:userId", nativeQuery = true)
    Label findById(long labelId, long userId);

    @Modifying
    @Query(value = "update label set label_title = :labelTitle where label_id = :labelId", nativeQuery = true)
    void update(String labelTitle, long labelId);

    @Modifying
    @Query(value = "delete from label where user_id = :userId and label_id = :labelId", nativeQuery = true)
    void delete(long userId, long labelId);

    @Query(value = "select * from label where user_id = :userId", nativeQuery = true)
    List<Label> getAll(long userId);

//    @Modifying
//    @Query(value = "insert into notes_labels(list_note_notes_id ,  labels_label_id)  values(:notesId, :labelId)", nativeQuery = true)
//    void insertdatatomap(long notesId, long labelId);
//
//    @Query(value = "select * from  notes_labels where list_note_notes_id = :notesId and labels_label_id =:labelId", nativeQuery = true)
//    Object findoneByLabelIdAndNoteId(long labelId, long notesId);
}
