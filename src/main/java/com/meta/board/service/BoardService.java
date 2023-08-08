package com.meta.board.service;

import com.meta.board.domain.board.Board;
import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardUpdateDto;
import com.meta.board.domain.Condition;
import com.meta.board.mapper.BoardMapper;
import com.meta.board.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;
    private final BoardMapper mapper;

    
    public void join(Board board){

        String encodePassword = passwordEncoder.encode(board.getPasswd());
        board.passwordEncoding(encodePassword);

        boardRepository.save(board);

/*        Long findBoardId = boardRepository.findBoardId();

        boardRepository.updateBoardGroup(findBoardId,findBoardId);*/
    }

    @Transactional(readOnly = true)
    public List<BoardDto> findAll(int offset, int pageSize, Condition condition){
        return boardRepository.findAll(offset,pageSize, condition);
    }
    
    public BoardDto findOne(Long id){
        BoardDto board = boardRepository.findById(id);
        mapper.addViewCount(id);
        return board;
    }

    public void updateBoard(Long id, BoardUpdateDto board){
        boardRepository.updateBoard(id,board);
    }

    public void deleteBoard(Long id){
        boardRepository.deleteBoard(id);
    }

    public boolean validPassWord(Long id, String target){

        String OriginPassWord = boardRepository.passwordCheck(id);

        if(!passwordEncoder.matches(target, OriginPassWord)){
            return false;
        }
        return true;
    }

    public int getCount(String keyword, String list){
        return boardRepository.count(keyword,list);
    }

    public String getTitle(Long id){
        String findTitle = boardRepository.findTitle(id);
        return  ("Re: " + findTitle).length() > 50 ? findTitle : "Re: " + findTitle;
    }
}
