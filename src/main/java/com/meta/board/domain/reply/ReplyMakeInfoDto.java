package com.meta.board.domain.reply;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyMakeInfoDto {
    private int board_group;
    private int board_group_order;
    private int board_depth;
}
