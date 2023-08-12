package com.meta.board.repository.board;

import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardUpdateDto;
import com.meta.board.domain.Condition;
import com.meta.board.domain.reply.ReplyMakeInfoDto;
import com.meta.board.domain.board.BoardMapper;
import com.meta.board.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository{

    private final BoardMapper mapper;

    @Override
    public void save(Board board) {
        mapper.save(board);
    }

    @Override
    public Long findBoardId() {
        return mapper.findLastBoardId();
    }

    @Override
    public void updateBoardGroup(Long id, Long board_group) {
        mapper.updateBoardGroup(id,board_group);
    }

    @Override
    public List<BoardDto> findAll(int offset, int pageSize, Condition condition) {
        return mapper.findAll(offset,pageSize, condition);
    }

    @Override
    public BoardDto findById(Long id) {
        return mapper.findOne(id);
    }

    @Override
    public void updateBoard(Long id, BoardUpdateDto board) {
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

    @Override
    public int count(String keyword, String list) {
        return mapper.count(keyword,list);
    }

    @Override
    public String findTitle(Long id) {
        return mapper.findTitle(id);
    }

    @Override
    public ReplyMakeInfoDto findInfoForMakeReply(Long id) {
        System.out.println(id);
        return mapper.findInfoForMakeReply(id);
    }


}
