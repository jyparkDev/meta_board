package com.meta.board.repository.comment;

import com.meta.board.domain.comment.CommentRequestDto;
import com.meta.board.domain.comment.CommentResponseDto;
import com.meta.board.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository{

    private final CommentMapper commentMapper;

    @Override
    public void save(CommentRequestDto commentRequestDto) {
        commentMapper.save(commentRequestDto);
    }

    @Override
    public List<CommentResponseDto> findAllByBoardId(Long id, int page, int pageSize) {
        return commentMapper.findAllByBoardId(id,page,pageSize);
    }

    @Override
    public int count(Long id) {
        return commentMapper.count(id);
    }

    @Override
    public String findPassword(Long id) {
        return commentMapper.findPassword(id);
    }

    @Override
    public void remove(Long id) {
        commentMapper.remove(id);
    }
}
