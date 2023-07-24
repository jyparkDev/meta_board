package com.meta.board.repository;

import com.meta.board.domain.BoardDto;
import com.meta.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {

    @Autowired
    private BoardMapper mapper;

    public List<BoardDto> findAll(){
        return mapper.findAll();
    }
}
