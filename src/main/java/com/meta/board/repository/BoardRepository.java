package com.meta.board.repository;

import com.meta.board.domain.BoardBean;
import com.meta.board.domain.BoardDto;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    void save(BoardBean boardBean);
    List<BoardDto> findAll();
    BoardDto findById(Long id);
    void updateBoard(Long id, BoardBean boardBean);
    void deleteBoard(Long id);
}
