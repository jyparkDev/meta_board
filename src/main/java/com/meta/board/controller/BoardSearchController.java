package com.meta.board.controller;

import com.meta.board.domain.BoardDto;
import com.meta.board.domain.PageHandler;
import com.meta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardSearchController {
    private final BoardService boardService;

    /**
     * 전체 조회 Controller
     */
    @GetMapping("/list")
    public String list(Integer page, Integer pageSize,String sort, String dir,Model model){

        if(page == null) page = 1;
        if(pageSize == null) pageSize = 10;
        model.addAttribute("sort" , sort);
        if (sort.equals("old_date")) sort = "create_date";
        if (dir == null) dir = "ASC";

        int totalCnt = boardService.getCount();
        PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);

        List<BoardDto> boardList = boardService.findAll((page - 1) * pageSize ,pageSize, sort,dir);

        model.addAttribute("boards", boardList);
        model.addAttribute("ph" , pageHandler);
        model.addAttribute("keyword" , "");
        model.addAttribute("dir" , dir);

        return "/board/list";
    }

    /**
     * 상세 조회 Controller
     */
    @GetMapping("/view")
    public String view(Long id,
                       int page, int pageSize,
                       @RequestParam(value = "sort", defaultValue = "create_date") String sort ,
                       @RequestParam(value = "dir", defaultValue = "ASC")String dir, Model model){

        BoardDto board = boardService.findOne(id);
        model.addAttribute("board",board);
        model.addAttribute("page",page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);

        return "/board/detail";
    }


    /**
     * 키워드 조회 Controller
     */
    @GetMapping("/search")
    public String searchKeyword(String keyword,String list, Integer page, Integer pageSize,Model model){
        if(page == null) page = 1;
        if(pageSize == null) pageSize = 10;

        int totalCnt = boardService.getKeyWordCount(keyword,list);
        PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);

        List<BoardDto> boardList = boardService.findByKeyword(keyword,list,(page - 1) * pageSize ,pageSize);

        model.addAttribute("boards",boardList);
        model.addAttribute("list",list);
        model.addAttribute("ph" , pageHandler);
        model.addAttribute("keyword",keyword);
        model.addAttribute("list",list);
        return "/board/list";
    }
}
