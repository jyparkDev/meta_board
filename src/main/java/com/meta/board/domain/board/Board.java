package com.meta.board.domain.board;

import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
    private String title;
    private String content;
    private String writer;
    private String passwd;
    public void passwordEncoding(String passwd){
        this.passwd = passwd;
    }

}
