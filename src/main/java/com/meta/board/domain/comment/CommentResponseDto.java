package com.meta.board.domain.comment;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@ToString
public class CommentResponseDto {

    private Long id;
    private String writer;
    private String content;
    private String createDate;
    private Long boardId;
}
