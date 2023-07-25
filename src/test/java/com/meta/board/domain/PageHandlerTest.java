package com.meta.board.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PageHandlerTest {

    @Test
    void 페이징(){
        PageHandler ph = new PageHandler(255,25);
        assertTrue(ph.getBeginPage() == 21);
        assertTrue(ph.getTotalPage() == 26);
    }
}