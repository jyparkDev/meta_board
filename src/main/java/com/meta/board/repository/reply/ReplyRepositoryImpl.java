package com.meta.board.repository.reply;

import com.meta.board.domain.reply.Reply;
import com.meta.board.domain.reply.ReplyMakeInfoDto;
import com.meta.board.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepository{

    private final ReplyMapper replyMapper;


    @Override
    public void updateBoardOrder(ReplyMakeInfoDto replyMakeInfoDto) {
        replyMapper.updateBoardOrder(replyMakeInfoDto);
    }

    @Override
    public void save(ReplyMakeInfoDto info, Reply reply) {
        replyMapper.save(info, reply);
    }
}
