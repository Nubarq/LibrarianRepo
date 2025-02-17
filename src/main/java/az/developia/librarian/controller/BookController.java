package az.developia.librarian.controller;

import az.developia.librarian.dto.request.BookRequest;
import az.developia.librarian.dto.response.BookResponse;
import az.developia.librarian.entity.Book;
import az.developia.librarian.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/registerBook")
    @PreAuthorize("hasAuthority('ADD_BOOK')")
    public ResponseEntity<BookResponse> registerBook(@RequestBody BookRequest bookRequest){
        return bookService.registerBook(bookRequest);
    }

    @DeleteMapping("/deleteBook")
    @PreAuthorize("hasAuthority('DELETE_BOOK')")
    public void deleteBook(@RequestParam Integer bookId){
        bookService.deleteBook(bookId);
    }

    @PutMapping("/editBook/{bookId}")
    @PreAuthorize("hasAuthority('EDIT_BOOK')")
    public Book editBook(@PathVariable Integer bookId, @RequestBody BookRequest updatedBook){
        return bookService.editBook(bookId,updatedBook);
    }

    @GetMapping("/searchBooks")
    @PreAuthorize("hasAuthority('SEARCH_BOOK')")
    public List<Book> searchBooks(@RequestParam(required = false) String title
            , @RequestParam(required = false) String author
            , @RequestParam(required = false) String isbn){
        return bookService.searchBooks(title,author,isbn);
    }
}
