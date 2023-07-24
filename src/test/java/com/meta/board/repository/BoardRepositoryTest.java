package com.meta.board.repository;

import com.meta.board.domain.BoardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void 게시글_전체조회(){
        List<BoardDto> boardDtos = boardRepository.findAll();
        assertThat(boardDtos.size()).isEqualTo(0);
    }
}