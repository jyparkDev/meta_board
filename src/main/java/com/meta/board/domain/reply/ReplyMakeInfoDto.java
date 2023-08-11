package com.meta.board.domain.reply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ReplyMakeInfoDto {
    private int boardGroup;
    private int boardGroupOrder;
    private int boardDepth;
}
