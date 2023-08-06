package com.meta.board.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private Long id;
    private String content;
    private String writer;
    private String passwd;

    public void passwordEncoding(String passwd){
        this.passwd = passwd;
    }
}
