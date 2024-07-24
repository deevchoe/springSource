package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DataRepository extends JpaRepository<Jikwon, Integer> {

    List<Jikwon> findByNum(int searchBuser);
    
    List<Jikwon> findByNumAndRating(int searchBuser, String searchRating);
}