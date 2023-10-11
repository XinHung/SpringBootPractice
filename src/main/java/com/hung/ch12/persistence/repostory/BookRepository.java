package com.hung.ch12.persistence.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.hung.ch12.persistence.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    
	@Query("from Book where title like %?1%")
    List<Book> findByTitle(String title);

    @Query(value = "select * from bookinfo where title like %?1%",
            countQuery = "select count(*) from bookinfo where like %?1%",
            nativeQuery = true)
    Page<Book> findAllBookByKeyword(String keyword, Pageable pageable);
    
	List<Book> findByAuthor(String author);
}
