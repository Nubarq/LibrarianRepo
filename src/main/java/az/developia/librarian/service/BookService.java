package az.developia.librarian.service;

import az.developia.librarian.dto.request.BookRequest;
import az.developia.librarian.dto.response.BookResponse;
import az.developia.librarian.entity.Book;

import java.util.List;

public interface BookService {
     BookResponse registerBook(BookRequest bookRequest);
     void deleteBook(Integer bookId);
     Book editBook(Integer bookId, BookRequest updatedBook);
    List<Book> searchBooks(String title, String author, String isbn);
}
