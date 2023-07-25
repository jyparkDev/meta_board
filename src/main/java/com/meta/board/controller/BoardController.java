package com.meta.board.controller;

import com.meta.board.domain.Board;
import com.meta.board.domain.BoardDto;
import com.meta.board.domain.PageHandler;
import com.meta.board.domain.PassWordRequestDto;
import com.meta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/")
    public String home(Integer page, Integer pageSize, Model model){

        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;

        int totalCnt = boardService.getCount();
        PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);

        List<BoardDto> boardList = boardService.findAll((page - 1) * pageSize ,pageSize);
        model.addAttribute("boards", boardList);
        model.addAttribute("ph" , pageHandler);

        return "/board/list";
    }

    @GetMapping("/board/write")
    public String writeForm(){
        return "/board/writeForm";
    }

    @PostMapping("/board/write")
    public String write(@ModelAttribute Board board){
        boardService.join(board);
        return "redirect:/";
    }

    @GetMapping("/board/view")
    public String detail(@RequestParam("id") Long id,
                         int page, int pageSize, Model model){
        BoardDto board = boardService.findOne(id);
        model.addAttribute("board",board);
        model.addAttribute("page",page);
        model.addAttribute("pageSize", pageSize);
        return "/board/view";
    }

    @GetMapping("/board/search")
    public String searchKeyword(@RequestParam("keyword") String keyword,@RequestParam("list") String list, Model model){
        List<BoardDto> boardList = boardService.findByKeyword(keyword,list);
        
        model.addAttribute("boards",boardList);
        model.addAttribute("list",list);
        return "/board/list";
    }

    @PostMapping("/board/passwd-check")
    @ResponseBody
    public Map<String , String> passwordCheck(@RequestBody PassWordRequestDto passWordRequestDto, HttpServletResponse res){
        Map<String , String> result = new HashMap<>();
        if(!boardService.validPassWord(passWordRequestDto.getId(), passWordRequestDto.getPassword())){
            res.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        result.put("status","OK");
        return result;
    }

    @PostMapping("/board/update")
    public String update(@ModelAttribute Board board,@RequestParam("id") Long id,Model model){
        boardService.updateBoard(id,board);
        BoardDto updateBoard = boardService.findOne(id);
        model.addAttribute("board",updateBoard);
        return "/board/view";
    }

    @GetMapping("/board/remove")
    public String remove(@RequestParam("id") Long id, @RequestParam("check") boolean check,HttpServletResponse res){
        if (!check){
            res.setStatus(HttpStatus.NOT_FOUND.value());
            return "/error/404";
        }
        boardService.deleteBoard(id);
        return "redirect:/";
    }
}
