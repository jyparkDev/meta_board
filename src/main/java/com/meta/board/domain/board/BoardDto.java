package com.meta.board.domain.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDto {
    private Long rnum;
    private Long id;
    private String writer;
    private String title;
    private String content;
    private int viewNum;
    private String createDate;
    private int commentCount;
    private int boardGroup;
    private int boardGroupOrder;
    private int boardDepth;
    private int exist;
    private int fileCount;

    public long getBoard_group() {
        return 0;
    }
}
