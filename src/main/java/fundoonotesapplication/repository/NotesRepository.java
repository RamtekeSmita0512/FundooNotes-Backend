package fundoonotesapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fundoonotesapplication.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes,Long> {

    @Query(value = "select * from note where notes_id = :notesId", nativeQuery = true)
    Notes findById(long notesId);

//    @Query(value = "select * from note where user_id = :userid", nativeQuery = true)
//    Notes findByuserid(long userid);

    @Modifying
    @Query(value = "insert into notes (notes_id,title,description,is_archived, is_deleted) values (:notesId, :title, :description,:isArchived, :isDeleted)" , nativeQuery = true)
    Notes insertData(long notesId, String title,String description,boolean isArchived, boolean isDeleted);

    @Modifying
    @Query(value = "update notes set is_deleted = :b where user_id = :userId AND id = :id" ,  nativeQuery = true)
    long delete(boolean b,long userId,long id);

    @Modifying
    @Query(value = "delete from notes  where user_id = :userId AND id = :id", nativeQuery = true)
    Notes deleteForever(long userId, long id);

//    @Modifying
//    @Query(value = "update notes set  description= :description , title = :title  where user_id = :id AND id = :id2", nativeQuery = true)
//    Notes updateData(String content, String title,long id, long id2);


    @Modifying
    @Query(value = "update notes set is_archived = :b where user_id = :userId AND id = :id", nativeQuery = true)
    Notes setArchive(boolean b, long userId, long id);

    @Query(value = "select * from notes where user_id = :userId", nativeQuery = true)
    List<Notes> getAll(Long userId);
    @Query(value = "select * from notes where user_id = :userId and notes_id = :notesId", nativeQuery = true)
    List<Notes> searchAllNotesByNoteId(long userId, long notesId);

}
