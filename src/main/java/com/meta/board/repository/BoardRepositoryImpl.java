package com.meta.board.repository;

import com.meta.board.domain.Board;
import com.meta.board.domain.BoardDto;
import com.meta.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository{

    private final BoardMapper mapper;

    @Override
    public void save(Board board) {
        mapper.save(board);
    }

    @Override
    public List<BoardDto> findAll() {
        return mapper.findAll();
    }

    @Override
    public List<BoardDto> findByKeyword(String keyword,String list) {
        return mapper.findByKeyword(keyword,list);
    }

    @Override
    public BoardDto findById(Long id) {
        return mapper.findOne(id);
    }

    @Override
    public void updateBoard(Long id, Board board) {
        mapper.update(id, board);
    }

    @Override
    public void deleteBoard(Long id) {
        mapper.delete(id);
    }

    @Override
    public String passwordCheck(Long id) {
        return mapper.getBoardPasswd(id);
    }
}
