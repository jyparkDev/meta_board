package com.meta.board.service;

import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardMapper;
import com.meta.board.model.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
        //@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BoardMapper mapper;

//    @BeforeEach
//    void 게시판_초기화() {
//        mapper.claer();
//    }

    @Test
    void 게시글_등록() throws IOException {
        Board board = Board.builder()
                .title("title")
                .content("content")
                .writer("tester")
                .passwd("#nomang12")
                .build();

        boardService.join(board);

        Long lastBoardId = mapper.findLastBoardId();
        BoardDto findBoard = boardService.findOne(lastBoardId);

        assertThat(findBoard.getId()).isEqualTo(findBoard.getBoard_group());

    }

//        @Test
//        void 게시글_전체조회() {
//            Board board1 = Board.builder()
//                    .title("test")
//                    .content("content")
//                    .writer("jypark")
//                    .passwd("a123")
//                    .build();
//            Board board2 = Board.builder()
//                    .title("test")
//                    .content("content")
//                    .writer("jypark")
//                    .passwd("a123")
//                    .build();
//            boardService.join(board1);
//            boardService.join(board2);
//            List<BoardDto> boardList = boardService.findAll(0, 10);
//            assertThat(boardList.size()).isEqualTo(2);
//        }
//
//        @Test
//        void 게시글_상세조회() {
//            Board board = Board.builder()
//                    .title("test")
//                    .content("content")
//                    .writer("jypark")
//                    .passwd("a123")
//                    .build();
//            boardService.join(board);
//            BoardDto result = boardService.findOne(1L);
//            assertThat(result.getId()).isEqualTo(1L);
//        }
//
//        @Test
//        void 게시글_수정() {
//            Board board = Board.builder()
//                    .title("test")
//                    .content("content")
//                    .writer("jypark")
//                    .passwd("a123")
//                    .build();
//            boardService.join(board);
//            Board upadteBoard = Board.builder().title("aa").content("변경").build();
//            boardService.updateBoard(1L, upadteBoard);
//            BoardDto findBoard = boardService.findOne(1L);
//            assertThat(findBoard.getTitle()).isNotEqualTo(board.getTitle());
//            assertThat(findBoard.getContent()).isNotEqualTo(board.getContent());
//        }
//
//        @Test
//        void 게시글_삭제() {
//            Board board = Board.builder()
//                    .title("test")
//                    .content("content")
//                    .writer("jypark")
//                    .passwd("a123")
//                    .build();
//            boardService.join(board);
//            String password = "a123";
//            boardService.deleteBoard(1L);
//            List<BoardDto> all = boardService.findAll(0, 10);
//            assertThat(all.size()).isEqualTo(0);
//        }
}

