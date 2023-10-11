package com.hung;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.hung.ch12.persistence.entity.Book;
import com.hung.ch12.persistence.repostory.BookRepository;

import jakarta.persistence.criteria.Predicate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    //@Test
    void saveBook() {
        Book book = new Book();
        book.setTitle("SpringBoot好簡單");
        book.setAuthor("王小華");
        book.setBookConcern("柏克萊出版社");
        book.setPublishDate(LocalDate.of(2022, 8, 5));
        book.setPrice(300.00f);
        book.setInventory(888);
        bookRepository.save(book);
    }

    //@Test
    void getBookById() {
        Optional<Book> optionalBook = bookRepository.findById(1);
        if(optionalBook.isPresent()) {
            System.out.println(optionalBook.get());
        }
    }
    
    //@Test
    void getBookByAuthor() {
        List<Book> bookList = bookRepository.findByAuthor("王小明5");
        if(!bookList.isEmpty()) {
            System.out.println(bookList);
        }
    }

    //@Test
    void getAllBooks() {
        List<Book> books = bookRepository.findAll();
        System.out.println(books);
    }

    //@Test
    void updateBook() {
        Optional<Book> optionalBook = bookRepository.findById(1);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setInventory(166);
            // save()方法也可用於更新資料，如果主鍵存在則執行更新；否則執行插入
            bookRepository.save(book);
        }
    }

    //@Test
    void deleteBook() {
        Optional<Book> optionalBook = bookRepository.findById(1);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookRepository.delete(book);
        }
    }
    
    @Test
    void getAllBooksByDateAndKeyword() {
        Specification specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.like(root.get("title"), "%" + "Java" + "%"));
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("publishDate"),
                    LocalDate.of(2020, 9, 1)));
            return criteriaBuilder.and(
                    predicates.toArray(new Predicate[predicates.size()]));
        };

        // 開始分頁查詢
        // 透過 PageRequest 的 Pageable 查詢第一頁，每頁 5 筆資料
        Pageable pageable = PageRequest.of(0, 5);
        List<Book> bookList = bookRepository.findByTitle("Java");
        System.out.println("bookList: " + bookList);
        
        Page<Book> page = bookRepository.findAll(specification, pageable);
        System.out.printf("總筆數：%d%n", page.getTotalElements());
        System.out.printf("當前頁數：%d頁%n", page.getNumber() + 1);
        System.out.printf("總頁數：%d页%n", page.getTotalPages());
        System.out.printf("當前頁面的紀錄數：%d%n", page.getNumberOfElements());
        System.out.println("當前頁面的內容為：");
        System.out.println(page.getContent());
    }
}