package com.meta.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
}
