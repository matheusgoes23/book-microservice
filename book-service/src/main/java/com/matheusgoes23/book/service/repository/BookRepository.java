package com.matheusgoes23.book.service.repository;

import com.matheusgoes23.book.service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
