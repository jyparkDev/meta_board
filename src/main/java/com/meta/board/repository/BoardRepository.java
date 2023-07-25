package com.meta.board.repository;

import com.meta.board.domain.Board;
import com.meta.board.domain.BoardDto;

import java.util.List;

public interface BoardRepository {
    void save(Board board);
    List<BoardDto> findAll(int offset, int pageSize);
    List<BoardDto> findByKeyword(String keyword, String list);
    BoardDto findById(Long id);
    void updateBoard(Long id, Board board);
    void deleteBoard(Long id);
    String passwordCheck(Long id);

    int count();
}
