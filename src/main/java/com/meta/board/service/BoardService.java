package com.meta.board.service;

import com.meta.board.domain.Board;
import com.meta.board.domain.BoardDto;
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

    public void join(Board board){
        String encodePassword = passwordEncoder.encode(board.getPasswd());
        board.passwordEncoding(encodePassword);
        boardRepository.save(board);
    }

    public List<BoardDto> findAll(int offset, int pagesize){
        return boardRepository.findAll(offset,pagesize);
    }

    public BoardDto findOne(Long id){
        return boardRepository.findById(id);
    }

    public void updateBoard(Long id, Board board){
        boardRepository.updateBoard(id,board);
    }

    public void deleteBoard(Long id){
        String OriginPassWord = boardRepository.passwordCheck(id);
        boardRepository.deleteBoard(id);
    }

    public List<BoardDto> findByKeyword(String keyword,String list){
        return boardRepository.findByKeyword(keyword,list);
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
}
