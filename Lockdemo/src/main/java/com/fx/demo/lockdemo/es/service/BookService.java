package com.fx.demo.lockdemo.es.service;

import com.fx.demo.lockdemo.es.entity.BookDO;

import java.util.List;

public interface BookService {

    BookDO getBookByAuthor(String author);

    List<BookDO> getBookListByName(String name);

}
