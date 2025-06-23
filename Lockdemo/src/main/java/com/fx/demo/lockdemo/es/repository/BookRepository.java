package com.fx.demo.lockdemo.es.repository;

import com.fx.demo.lockdemo.es.entity.BookDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<BookDO, String> {

    /**
     * 精确匹配 term
     * @param auth
     * @return
     */
    List<BookDO> findByAuth(String auth);

    List<BookDO> findByCount(Long count);

    /**
     * 模糊匹配 - 分词 match
     * @param keyword
     * @return
     */
    List<BookDO> findByNameContaining(String keyword);


    Page<BookDO> findByDescContaining(String desc, Pageable pageable);

}
