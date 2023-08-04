package com.meta.board.repository.reply;

import com.meta.board.domain.reply.Reply;
import com.meta.board.domain.reply.ReplyMakeInfoDto;

public interface ReplyRepository {
    void updateBoardOrder(ReplyMakeInfoDto replyMakeInfoDto);

    void save(ReplyMakeInfoDto info, Reply reply);
}
