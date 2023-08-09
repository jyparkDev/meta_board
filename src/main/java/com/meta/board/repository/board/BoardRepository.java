package com.meta.board.repository.board;

import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardUpdateDto;
import com.meta.board.domain.Condition;
import com.meta.board.domain.reply.ReplyMakeInfoDto;
import com.meta.board.model.Board;

import java.util.List;

public interface BoardRepository {
    void save(Board board);

    Long findBoardId();
    void updateBoardGroup(Long id, Long board_group);

    List<BoardDto> findAll(int offset, int pageSize, Condition condition);
    BoardDto findById(Long id);
    void updateBoard(Long id, BoardUpdateDto board);
    void deleteBoard(Long id);
    String passwordCheck(Long id);

    int count(String keyword, String list);

    String findTitle(Long id);

    ReplyMakeInfoDto findInfoForMakeReply(Long id);


}
