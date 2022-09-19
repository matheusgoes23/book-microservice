package com.matheusgoes23.book.service.controller;

import com.matheusgoes23.book.service.model.Book;
import com.matheusgoes23.book.service.proxy.CambioProxy;
import com.matheusgoes23.book.service.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public record BookController(Environment environment, BookRepository repository, CambioProxy proxy) {

    @Operation(summary = "Find a specific book by your ID")
    @GetMapping("{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

        var book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");

        book.setEnvironment("Book Port: " + port + " Cambio Port: " + cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());

        return book;
    }
}
