package com.meta.board.service;

import com.meta.board.domain.Board;
import com.meta.board.domain.BoardDto;
import com.meta.board.mapper.BoardMapper;
import com.meta.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;
    private final BoardMapper mapper;
    public void join(Board board){
        String encodePassword = passwordEncoder.encode(board.getPasswd());
        board.passwordEncoding(encodePassword);
        boardRepository.save(board);
    }

    public List<BoardDto> findAll(int offset, int pagesize){
        return boardRepository.findAll(offset,pagesize);
    }

    public BoardDto findOne(Long id){
        BoardDto board = boardRepository.findById(id);
        mapper.addViewCount(id);
        return board;
    }

    public void updateBoard(Long id, Board board){
        boardRepository.updateBoard(id,board);
    }

    public void deleteBoard(Long id){
        String OriginPassWord = boardRepository.passwordCheck(id);
        boardRepository.deleteBoard(id);
    }

    public List<BoardDto> findByKeyword(String keyword,String list, int page, int pageSize){
        return boardRepository.findByKeyword(keyword,list,page,pageSize);
    }

    public boolean validPassWord(Long id, String target){

        String OriginPassWord = boardRepository.passwordCheck(id);
        if(!passwordEncoder.matches(target, OriginPassWord)){
            System.out.println("Aaa");
            return false;
        }
        return true;
    }

    public int getCount(){
        return boardRepository.count();
    }
    public int getKeyWordCount(String keyword,String list){
        return boardRepository.keywordCount(keyword,list);
    }
}
