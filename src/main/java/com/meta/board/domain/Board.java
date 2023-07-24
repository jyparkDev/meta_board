package com.meta.board.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Board {
    private String title;
    private String content;
    private String writer;
    private String passwd;

    public void passwordEncoding(String passwd){
        this.passwd = passwd;
    }

}
