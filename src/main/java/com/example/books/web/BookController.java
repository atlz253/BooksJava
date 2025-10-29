package com.example.books.web;

import com.example.books.domain.Book;
import com.example.books.service.BookService;
import com.example.books.web.dto.BookCreateRequest;
import com.example.books.web.dto.BookUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody BookCreateRequest req) {
        Book saved = service.create(req.getTitle(), req.getAuthor(), req.getYear(), req.getDescription());
        return ResponseEntity
                .created(URI.create("/api/books/" + saved.getId()))
                .body(saved); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody BookUpdateRequest req) {
        Book saved = service.update(id, req.getTitle(), req.getAuthor(), req.getYear(), req.getDescription());
        return ResponseEntity.ok(saved); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
