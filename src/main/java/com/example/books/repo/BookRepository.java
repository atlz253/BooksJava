package com.example.books.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.books.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
