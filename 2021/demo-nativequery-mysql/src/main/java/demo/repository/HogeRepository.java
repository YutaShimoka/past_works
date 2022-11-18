package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import demo.entity.Hoge;

@Repository
public interface HogeRepository extends JpaRepository<Hoge, Integer> {

    @Query(value = "select * from hoge where json_search(items, 'one', ?1) is not null;", nativeQuery = true)
    List<Hoge> findBySourceId(String sourceId);
}
