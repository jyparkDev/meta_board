package com.meta.board.service;

import com.meta.board.domain.Board;
import com.meta.board.domain.BoardDto;
import com.meta.board.mapper.BoardMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BoardMapper mapper;

    @BeforeEach
    void 게시판_초기화(){
        mapper.claer();
    }

    @Test
    void 게시글_등록() {
        Board board = Board.builder()
                .title("test")
                .content("content")
                .writer("jypark")
                .passwd("a123")
                .build();
        boardService.join(board);
        List<BoardDto> boardList = boardService.findAll(0,10);
        assertThat(boardList.size()).isEqualTo(1);
    }

    @Test
    void 게시글_전체조회(){
        Board board1 = Board.builder()
                .title("test")
                .content("content")
                .writer("jypark")
                .passwd("a123")
                .build();
        Board board2 = Board.builder()
                .title("test")
                .content("content")
                .writer("jypark")
                .passwd("a123")
                .build();
        boardService.join(board1);
        boardService.join(board2);
        List<BoardDto> boardList = boardService.findAll(0,10);
        assertThat(boardList.size()).isEqualTo(2);
    }

    @Test
    void 게시글_상세조회(){
        Board board = Board.builder()
                .title("test")
                .content("content")
                .writer("jypark")
                .passwd("a123")
                .build();
        boardService.join(board);
        BoardDto result = boardService.findOne(1L);
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void 게시글_수정(){
        Board board = Board.builder()
                .title("test")
                .content("content")
                .writer("jypark")
                .passwd("a123")
                .build();
        boardService.join(board);
        Board upadteBoard = Board.builder().title("aa").content("변경").build();
        boardService.updateBoard(1L, upadteBoard);
        BoardDto findBoard = boardService.findOne(1L);
        assertThat(findBoard.getTitle()).isNotEqualTo(board.getTitle());
        assertThat(findBoard.getContent()).isNotEqualTo(board.getContent());
    }

    @Test
    void 게시글_삭제(){
        Board board = Board.builder()
                .title("test")
                .content("content")
                .writer("jypark")
                .passwd("a123")
                .build();
        boardService.join(board);
        String password = "a123";
        boardService.deleteBoard(1L);
        List<BoardDto> all = boardService.findAll(0,10);
        assertThat(all.size()).isEqualTo(0);
    }

}