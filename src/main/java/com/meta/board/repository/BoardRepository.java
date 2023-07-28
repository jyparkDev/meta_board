package com.meta.board.repository;

import com.meta.board.domain.Board;
import com.meta.board.domain.BoardDto;
import com.meta.board.domain.BoardUpdateDto;
import com.meta.board.domain.Condition;

import java.util.List;

public interface BoardRepository {
    void save(Board board);
    List<BoardDto> findAll(int offset, int pageSize, Condition condition);
    List<BoardDto> findByKeyword(String keyword, String list,int offset,int pageSize);
    BoardDto findById(Long id);
    void updateBoard(Long id, BoardUpdateDto board);
    void deleteBoard(Long id);
    String passwordCheck(Long id);

    int count(String keyword, String list);

}
