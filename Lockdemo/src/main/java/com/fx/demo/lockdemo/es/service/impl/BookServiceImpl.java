package com.fx.demo.lockdemo.es.service.impl;

import com.fx.demo.lockdemo.es.entity.BookDO;
import com.fx.demo.lockdemo.es.repository.BookRepository;
import com.fx.demo.lockdemo.es.service.BookService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookRepository bookRepository;

    @Override
    public BookDO getBookByAuthor(String author) {
        System.out.println("author----->>>" + author);
        List<BookDO> byAuth = bookRepository.findByAuth(author);
        if (CollectionUtils.isEmpty(byAuth)) {
            return null;
        }
        System.out.println("size----->>>" + byAuth.size());
        return byAuth.get(0);
    }

    @Override
    public List<BookDO> getBookListByName(String name) {
        List<BookDO> nameContaining = bookRepository.findByNameContaining(name);
        System.out.println("nameContainSize------>>" + nameContaining.size());
        return nameContaining;
    }

//    public
}
