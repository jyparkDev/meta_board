package com.meta.board.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String create_date;
    private String modified_date;
}
