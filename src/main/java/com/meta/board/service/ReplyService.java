package com.meta.board.service;

import com.meta.board.domain.reply.Reply;
import com.meta.board.domain.reply.ReplyMakeInfoDto;
import com.meta.board.repository.board.BoardRepository;
import com.meta.board.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(Reply reply){
        String encodingPassWord = passwordEncoder.encode(reply.getPasswd());
        reply.passwordEncoding(encodingPassWord);

        ReplyMakeInfoDto makeSource = boardRepository.findInfoForMakeReply(reply.getId());
        replyRepository.updateBoardOrder(makeSource);

        replyRepository.save(makeSource,reply);
    }


}
