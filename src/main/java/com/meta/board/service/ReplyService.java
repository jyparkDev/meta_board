package com.meta.board.service;

import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardMapper;
import com.meta.board.domain.reply.Reply;
import com.meta.board.domain.reply.ReplyMakeInfoDto;
import com.meta.board.repository.board.BoardRepository;
import com.meta.board.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final PasswordEncoder passwordEncoder;
    private final BoardMapper mapper;

    public void join(Reply reply){
        String encodingPassWord = passwordEncoder.encode(reply.getPasswd());
        reply.passwordEncoding(encodingPassWord);

        log.info("id : {}", reply.getId());
        ReplyMakeInfoDto makeReply = boardRepository.findInfoForMakeReply(reply.getId());
        log.info("source : {}", makeReply);
        replyRepository.updateBoardOrder(makeReply);

        replyRepository.save(makeReply,reply);
    }


}
