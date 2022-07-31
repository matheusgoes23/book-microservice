package com.matheusgoes23.book.service.controller;

import com.matheusgoes23.book.service.model.Book;
import com.matheusgoes23.book.service.proxy.CambioProxy;
import com.matheusgoes23.book.service.repository.BookRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book-service")
public record BookController(Environment environment,
                             BookRepository repository,
                             CambioProxy proxy) {

    @GetMapping("{id}/{currency}")
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency) {

        var book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        var cambio = proxy.getCambio(
                book.getPrice(),
                "USD",
                currency);

        var port = environment.getProperty("local.server.port");

        book.setEnvironment(port);
        book.setPrice(cambio.getConvertedValue());

        return book;
    }
}
