package com.meta.board.service;

import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardUpdateDto;
import com.meta.board.domain.Condition;
import com.meta.board.domain.board.BoardMapper;
import com.meta.board.domain.file.FileMapper;
import com.meta.board.domain.file.FileRequest;
import com.meta.board.domain.file.FileUtils;
import com.meta.board.model.Board;
import com.meta.board.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;
    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;
    private final FileUtils fileUtils;
    private final FileService fileService;

    
    public void join(Board board) throws IOException {

        // 패스워드 암호화
        String encodePassword = passwordEncoder.encode(board.getPasswd());
        board.passwordEncoding(encodePassword);

        // 게시글 등록
        boardRepository.save(board);

        Long boardId = board.getId();

        List<MultipartFile> files = board.getFiles();

        if(Objects.isNull(files)){
            return;
        }

        List<FileRequest> fileRequestList = fileUtils.uploadFiles(files);

        fileService.saveFiles(boardId,fileRequestList);

    }

    @Transactional(readOnly = true)
    public List<BoardDto> findAll(int offset, int pageSize, Condition condition){
        List<BoardDto> boardList = boardRepository.findAll(offset, pageSize, condition);
        int rnum = boardMapper.originBoardCount(offset);
        for (BoardDto boardDto : boardList) {
            if (boardDto.getBoardDepth() == 0){
                boardDto.setRnum((long)rnum--);
            }
        }
        return boardList;
    }
    
    public BoardDto findOne(Long id){
        BoardDto board = boardRepository.findById(id);
        boardMapper.addViewCount(id);
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
