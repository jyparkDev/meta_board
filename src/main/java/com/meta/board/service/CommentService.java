package com.meta.board.service;

import com.meta.board.domain.PageHandler;
import com.meta.board.domain.comment.CommentRequestDto;
import com.meta.board.domain.comment.CommentResponseDto;
import com.meta.board.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    public Map<String,Object> join(CommentRequestDto commentRequestDto, Map<String, Object> model){
        String passwd = commentRequestDto.getPasswd();
        String encodingPasswd = passwordEncoder.encode(passwd);
        commentRequestDto.passwordEncoding(encodingPasswd);
        commentRepository.save(commentRequestDto);

        Long board_id = commentRequestDto.getId();
        int totalCount = commentRepository.count(board_id);

        PageHandler pageHandler = new PageHandler(totalCount,1,5);

        List<CommentResponseDto> comments = commentRepository.findAllByBoardId(board_id, (pageHandler.getPage() - 1) * pageHandler.getPageSize(), pageHandler.getPageSize());
        model.put("comments",comments);
        model.put("page",pageHandler);

        return model;
    }

    public Map<String,Object> searchList( Long id, int page, Map<String, Object> model){

        Long board_id = id;
        int totalCount = commentRepository.count(board_id);

        PageHandler pageHandler = new PageHandler(totalCount,page,5);

        List<CommentResponseDto> comments = commentRepository.findAllByBoardId(board_id, (pageHandler.getPage() - 1) * pageHandler.getPageSize(), pageHandler.getPageSize());
        model.put("comments",comments);
        model.put("page",pageHandler);

        return model;
    }

    public boolean passwdCheck(Long id, String passwd){
        String findPasswd = commentRepository.findPassword(id);

        return passwordEncoder.matches(passwd, findPasswd);
    }

    public Map<String,Object> removeComment(Long board_id, int page, Long commentId, Map<String, Object> model){

        commentRepository.remove(commentId);

        int totalCount = commentRepository.count(board_id);

        PageHandler pageHandler = new PageHandler(totalCount, page,5);

        List<CommentResponseDto> comments = commentRepository.findAllByBoardId(board_id, (pageHandler.getPage() - 1) * pageHandler.getPageSize(), pageHandler.getPageSize());
        model.put("comments",comments);
        model.put("page",pageHandler);

        return model;

    }
}
