package com.meta.board.repository.comment;

import com.meta.board.domain.comment.CommentRequestDto;
import com.meta.board.domain.comment.CommentResponseDto;

import java.util.List;

public interface CommentRepository {

    void save(CommentRequestDto commentRequestDto);

    List<CommentResponseDto> findAllByBoardId(Long id, int page, int pageSize);
    int count(Long id);

    String findPassword(Long id);

    void remove(Long id);
}
