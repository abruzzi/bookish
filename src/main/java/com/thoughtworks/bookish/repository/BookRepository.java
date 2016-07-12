package com.thoughtworks.bookish.repository;


import com.thoughtworks.bookish.model.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query("SELECT b FROM Book b INNER JOIN b.users u WHERE u.email=:email")
    Iterable<Book> findByUserEmail(@Param("email") String email);
}
