package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {
    //    private BookRepository bookRepository;
    //    private AuthorRepository authorRepository;
    //
    //    public Mutation(
    //            BookRepository bookRepository,
    //            AuthorRepository authorRepository
    //    ) {
    //        this.bookRepository = bookRepository;
    //        this.authorRepository = authorRepository;
    //    }
    //
    //    public Author newAuthor(String firstName, String lastName) {
    //        Author author = new Author();
    //        author.setFirstName(firstName);
    //        author.setLastName(lastName);
    //
    //        authorRepository.save(author);
    //
    //        return author;
    //    }
    //
    //    public Book newBook(
    //            String title,
    //            String isbn,
    //            Integer pageCount,
    //            Long authorId
    //    ) {
    //        Book book = new Book();
    //        book.setAuthor(new Author(authorId));
    //        book.setTitle(title);
    //        book.setIsbn(isbn);
    //        book.setPageCount(pageCount != null ? pageCount : 0);
    //
    //        bookRepository.save(book);
    //
    //        return book;
    //    }
    //
    //    public boolean deleteBook(Long id) {
    //        bookRepository.delete(
    //                bookRepository
    //                        .findById(id)
    //                        .orElseThrow(() -> new BookNotFoundException(id))
    //        );
    //        return true;
    //    }
    //
    //    public Book updateBookPageCount(Integer pageCount, Long id) {
    //        Book book = bookRepository
    //                .findById(id)
    //                .orElseThrow(() -> new BookNotFoundException(id));
    //        book.setPageCount(pageCount);
    //
    //        bookRepository.save(book);
    //
    //        return book;
    //    }
}
