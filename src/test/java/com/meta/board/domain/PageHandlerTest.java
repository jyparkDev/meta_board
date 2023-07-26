package com.meta.board.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class PageHandlerTest {

    /**
     * 네비게이션 블럭 범위는 10으로 한다.
     */
    @Test
    void 페이지_개수_테스트(){
        int page = 1;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(101,page, pageSize);
        assertThat(pageHandler.getTotalPage()).isEqualTo(11);
    }

    @Test
    void 네비게이션_시작_범위(){
        int page = 25;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(250,page,pageSize);
        assertThat(pageHandler.getBeginPage()).isEqualTo(21);
    }

    @Test
    void 네비게이션_마지막_페이지(){
        int page = 25;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(250,page,pageSize);
        assertThat(pageHandler.getEndPage()).isEqualTo(page);
    }

    @Test
    void 이전_블럭_비활성화(){
        int page = 10;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(250,page,pageSize);

        assertThat(pageHandler.isPrev()).isFalse();
    }

    @Test
    void 이전_블럭_활성화(){
        int page = 11;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(250,page,pageSize);

        assertThat(pageHandler.isPrev()).isTrue();
    }

    @Test
    void 다음_블럭_비활성화(){
        int page = 20;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(200,page,pageSize);

        assertThat(pageHandler.isNext()).isFalse();
    }

    @Test
    void 다음_블럭_활성화(){
        int page = 10;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(200,page,pageSize);

        assertThat(pageHandler.isNext()).isTrue();
    }

    @Test
    void 첫_페이지_활성화(){
        int page = 2;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(200,page,pageSize);
        assertThat(pageHandler.isFirst()).isTrue();
    }

    @Test
    void 첫_페이지_비활성화(){
        int page = 1;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(200,page,pageSize);
        assertThat(pageHandler.isFirst()).isFalse();
    }

    @Test
    void 마지막_페이지_활성화(){
        int page = 19;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(200,page,pageSize);
        assertThat(pageHandler.isEnd()).isTrue();
    }

    @Test
    void 마지막_페이지_비활성화(){
        int page = 20;
        int pageSize = 10;
        PageHandler pageHandler = new PageHandler(200,page,pageSize);
        assertThat(pageHandler.isEnd()).isFalse();
    }

}