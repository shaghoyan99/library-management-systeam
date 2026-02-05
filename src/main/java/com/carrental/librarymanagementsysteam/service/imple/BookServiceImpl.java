package com.carrental.librarymanagementsysteam.service.imple;

import com.carrental.librarymanagementsysteam.exception.BookNotFoundException;
import com.carrental.librarymanagementsysteam.model.Book;
import com.carrental.librarymanagementsysteam.repository.BookRepository;
import com.carrental.librarymanagementsysteam.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAllByCategoryId(Integer categoryId) {
        return bookRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Book> findAllByTitleContainingIgnoreCase(String search) {
        return bookRepository.findAllByTitleContainingIgnoreCase(search);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id " + id + "not found."));
    }


    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }
}
