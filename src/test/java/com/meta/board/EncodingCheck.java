package com.meta.board;

import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EncodingCheck {

    @Autowired
    BoardMapper mapper ;
    @Test
    void 문자크기(){
        BoardDto alpha = mapper.findOne((long) 216);
        BoardDto korean = mapper.findOne((long) 217);

        System.out.println(alpha.getTitle().getBytes().length);
        System.out.println(korean.getTitle().getBytes().length);
    }
}
