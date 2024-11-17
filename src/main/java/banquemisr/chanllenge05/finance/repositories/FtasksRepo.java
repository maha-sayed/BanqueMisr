package banquemisr.chanllenge05.finance.repositories;

import banquemisr.chanllenge05.finance.entities.FtasksEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface FtasksRepo extends JpaRepository<FtasksEntity,Integer> {

//    Page<FtasksEntity> findAll(Pageable pageData);
    List<FtasksEntity> findByStatus(String status);
    List<FtasksEntity> findByDueDate(Date dueDate);
}
