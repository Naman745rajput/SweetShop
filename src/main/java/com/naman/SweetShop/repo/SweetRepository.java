package com.naman.SweetShop.repo;


import com.naman.SweetShop.model.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SweetRepository extends JpaRepository<Sweet, Long> {

    @Query("SELECT s FROM Sweet s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Sweet> searchSweets(@Param("keyword") String keyword);
}
