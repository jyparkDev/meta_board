package com.meta.board.repository;

import com.meta.board.domain.BoardBean;
import com.meta.board.domain.BoardDto;
import com.meta.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

    @Autowired
    private BoardMapper mapper;

    @Override
    public void save(BoardBean boardBean) {
        mapper.save(boardBean);
    }

    @Override
    public List<BoardDto> findAll() {
        return mapper.findAll();
    }

    @Override
    public BoardDto findById(Long id) {
        return mapper.findOne(id);
    }

    @Override
    public void updateBoard(Long id, BoardBean boardBean) {
        mapper.update(id, boardBean);
    }

    @Override
    public void deleteBoard(Long id) {
        mapper.delete(id);
    }
}
