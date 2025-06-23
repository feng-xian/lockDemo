package com.fx.demo.lockdemo.es;

import com.fx.demo.lockdemo.es.entity.BookDO;
import com.fx.demo.lockdemo.es.service.BookService;
import org.apache.commons.compress.utils.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest
public class BookServiceTest {

    @Resource
    private BookService bookService;


    @Test
    public void test_getAuthor() {
        BookDO bookByAuthor = bookService.getBookByAuthor("崔永元");
        if (Objects.isNull(bookByAuthor)) {
            System.out.println("isnull");
        }
        else {
            System.out.println(bookByAuthor.toString());
        }

    }

    @Test
    public void test_nameContain() {
        List<BookDO> listByName = bookService.getBookListByName("有");
        Optional.ofNullable(listByName).orElse(Lists.newArrayList()).forEach(System.out::println);
    }

}
