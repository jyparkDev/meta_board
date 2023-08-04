package com.meta.board.domain.board;

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
    private int comment_count;
    private int board_group;
    private int board_depth;
}
