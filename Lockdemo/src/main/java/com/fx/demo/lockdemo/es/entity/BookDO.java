package com.fx.demo.lockdemo.es.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Data
@Document(indexName = "book")
public class BookDO {

    private String id;
    private String auth;
    private Long count;
    private LocalDateTime createtime;
    private String name;
    private String desc;

}
