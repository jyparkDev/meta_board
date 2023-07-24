package com.meta.board.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardBean {
    private String title;
    private String content;
    private String writer;
    private String passwd;
}
