package com.meta.board.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardDto {
    private Long rnum;
    private Long id;
    private String writer;
    private String title;
    private String content;
    private int viewNum;
    private String create_date;
}
