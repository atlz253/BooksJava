package com.example.books.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.books.domain.Book;
import com.example.books.repo.BookRepository;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> findAll() {
        return repo.findAll();
    }

    public Book findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Book " + id + " not found"));
    }

    @Transactional
    public Book create(String title, String author, Integer year, String description) {
        Book b = new Book(title, author, year, description);
        return repo.save(b);
    }

    @Transactional
    public Book update(Long id, String title, String author, Integer year, String description) {
        Book b = repo.findById(id).orElseThrow(() -> new NotFoundException("Book " + id + " not found"));
        b.setTitle(title);
        b.setAuthor(author);
        b.setYear(year);
        b.setDescription(description);
        return repo.save(b);
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Book " + id + " not found");
        }
        repo.deleteById(id);
    }
}
