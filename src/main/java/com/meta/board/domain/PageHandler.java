package com.meta.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PageHandler {
    private int totalCnt; // 총 게시물 갯수
    private int pageSize; // 한 페이지의 크기
    private int naviSize = 10; // 페이지 내비게이션의 크기
    private int totalPage; // 전체 페이지의 갯수
    private int page; // 현재 페이지
    private int beginPage; // 내비게이션의 첫 페이지
    private int endPage; // 내비게이션의 마지막 페이지
    private boolean prev; // 이전 페이지로 이동하는 링크를 보여줄 것인지 여부
    private boolean next; // 이전 페이지로 이동하는 링크를 보여줄 것인지 여부
    private boolean first; // 가장 앞 블럭으로
    private boolean end; // 가장 마지막 블럭으로

    public PageHandler(int totalCnt, int page) {
        this(totalCnt, page, 10);
    }

    public PageHandler(int totalCnt, int page, int pageSize) {
        if (totalCnt == 0) return;
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize = pageSize;

        totalPage = (int) Math.ceil((double) totalCnt / pageSize);
        beginPage = (page - 1) / naviSize * naviSize + 1;

        endPage = Math.min(beginPage + naviSize - 1, totalPage);
        prev = beginPage != 1;
        next = endPage != totalPage;
        first = page != 1;
        end = page != totalPage;



    }
}
