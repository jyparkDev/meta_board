package com.meta.board.domain.reply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Reply {
    private Long id;
    private String title;
    private String writer;
    private String passwd;
    private String content;
    public void passwordEncoding(String passwd){
        this.passwd = passwd;
    }
}
