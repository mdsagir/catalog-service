//package com.example.controller;
//
//import com.example.model.Book;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.JsonTest;
//import org.springframework.boot.test.json.JacksonTester;
//
//import java.time.Year;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@JsonTest
//public class JSONBookControllerTestCase {
//
//    @Autowired
//    private JacksonTester<Book> json;
//
//    @Test
//    void testSerialize() throws Exception {
//        Book book = new Book("1234567890", "Title", "Author", Year.of(1973), 9.90);
//        assertThat(json.write(book)).extractingJsonPathStringValue("@.isbn")
//                .isEqualTo("1234567890");
//        assertThat(json.write(book)).extractingJsonPathStringValue("@.title")
//                .isEqualTo("Title");
//        assertThat(json.write(book)).extractingJsonPathStringValue("@.author")
//                .isEqualTo("Author");
//        assertThat(json.write(book)).extractingJsonPathStringValue("@.publishingYear")
//                .isEqualTo("1973");
//        assertThat(json.write(book)).extractingJsonPathNumberValue("@.price")
//                .isEqualTo(9.90);
//    }
//
//    @Test
//    void testDeserialize() throws Exception {
//        String content = "{\"isbn\":\"1234567890\",\"title\":\"Title\", \"author\":\"Author\",\"publishingYear\":\"1973\", \"price\":9.90}";
//        assertThat(json.parse(content))
//                .usingRecursiveComparison().isEqualTo(new Book("1234567890", "Title", "Author",
//                Year.of(1973), 9.90));
//        assertThat(json.parseObject(content).getIsbn()).isEqualTo("1234567890");
//    }
//}
