package com.meta.board.repository;

import com.meta.board.domain.Board;
import com.meta.board.domain.BoardDto;
import com.meta.board.mapper.BoardMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepositoryImpl boardRepositoryImpl;
    @Autowired
    BoardMapper mapper;

    @BeforeEach
    void 테이블_초기화(){
        mapper.claer();
    }
    @Test
    void 게시글_등록(){
        boardRepositoryImpl.save(Board.builder().title("a").content("A").writer("test").passwd("aaa").build());
        List<BoardDto> boardList = boardRepositoryImpl.findAll();
        assertThat(boardList.size()).isEqualTo(1);
    }

    @Test
    void 게시글_전체조회(){
        boardRepositoryImpl.save(Board.builder().title("a").content("A").writer("test").passwd("aaa").build());
        List<BoardDto> boardDtos = boardRepositoryImpl.findAll();
        assertThat(boardDtos.size()).isEqualTo(1);
    }

    @Test
    void 게시글_상세조회(){
        boardRepositoryImpl.save(Board.builder().title("a").content("A").writer("test").passwd("aaa").build());
        BoardDto findBoard = boardRepositoryImpl.findById(1L);
        assertThat(findBoard.getId()).isEqualTo(1L);
        System.out.println(findBoard.toString());
    }

    @Test
    void 게시글_수정(){
        boardRepositoryImpl.save(Board.builder().title("a").content("A").writer("test").passwd("aaa").build());
        Board updateBoard = Board.builder().title("aa").content("A").build();
        boardRepositoryImpl.updateBoard(1L, updateBoard);

        BoardDto findBoard = boardRepositoryImpl.findById(1L);
        assertThat(findBoard.getTitle()).isEqualTo("aa");
    }

    @Test
    void 게시글_삭제(){
        boardRepositoryImpl.save(Board.builder().title("a").content("A").writer("test").passwd("aaa").build());
        boardRepositoryImpl.deleteBoard(1L);
        List<BoardDto> boardDtos = boardRepositoryImpl.findAll();
        assertThat(boardDtos.size()).isEqualTo(0);
    }

}